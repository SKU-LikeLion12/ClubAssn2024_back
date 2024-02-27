package likelion12.puzzle.service;

import likelion12.puzzle.domain.DateCheck;
import likelion12.puzzle.repository.DateCheckRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Service
@RequiredArgsConstructor
public class DateCheckService {

    private final DateCheckRepository dateCheckRepository;

    public LocalDateTime expireOfferDate() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        now = now.plusDays(1);
        while (isBreak(now.toLocalDate())) {
            now = now.plusDays(1);
        }
        if (now.getDayOfWeek().getValue() == 5) { // 금요일인 경우
            now = now.with(LocalTime.of(15, 0));
        } else {
            now = now.with(LocalTime.of(17, 30));
        }

        return now;
    }

    @Transactional
    public Boolean isBreak(LocalDate date){

        DateCheck byDate = dateCheckRepository.findByDate(date);
        if(byDate!=null){
            return byDate.getIsBreak();
        }
        try {
            byDate = new DateCheck(date, (isWeekend(date) || isHoliday(date)));
            dateCheckRepository.save(byDate);
            return byDate.getIsBreak();
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return null;
    }

    private boolean isWeekend(LocalDate date){
        return date.getDayOfWeek().getValue() >= 6;
    }

    private boolean isHoliday(LocalDate date) throws Exception {
        String response = getAPIResponse(getHolidayUrl(date));
        return parseHolidayResponse(response, date);
    }

    private String getHolidayUrl(LocalDate date) {
        return "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo" +
                "?serviceKey=1Kw49paSD5t9RqepmPwQpD8cNSs0I1SzwQlnt5n62oU6vD5P%2Fxnnp2t3I8PwfAa%2BtFY02jbP6yWFcfiKPMRWqA%3D%3D" +
                "&solYear=" + date.getYear() +
                "&solMonth=" + String.format("%02d", date.getMonthValue()) +
                "&_type=json";
    }

    private String getAPIResponse(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        connection.disconnect();

        return response.toString();
    }

    private boolean parseHolidayResponse(String response, LocalDate date) {
        JSONObject jsonResponse = new JSONObject(response);

        Object itemObject = jsonResponse.getJSONObject("response").getJSONObject("body").getJSONObject("items").get("item");

        JSONArray holidayList = new JSONArray();
        if (itemObject instanceof JSONArray) {
            holidayList = (JSONArray) itemObject;
        } else if (itemObject instanceof JSONObject) {
            holidayList.put((JSONObject) itemObject);
        }

        String targetDate = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        for (int i = 0; i < holidayList.length(); i++) {
            JSONObject holiday = holidayList.getJSONObject(i);
            Integer locdate = holiday.getInt("locdate"); // 공휴일 날짜
            if (targetDate.equals(locdate.toString())) {
                return true;
            }
        }
        return false;
    }

}