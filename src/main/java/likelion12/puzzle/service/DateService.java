package likelion12.puzzle.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class DateService {
    public static LocalDateTime expireOfferDate() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        now = now.plusDays(1);
        try {
            while (isHoliday(now.toLocalDate())) {
                now = now.plusDays(1);
            }
            if (now.getDayOfWeek().getValue() == 5) { // 금요일인 경우
                now = now.with(LocalTime.of(15, 0));
            } else {
                now = now.with(LocalTime.of(17, 30));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return now;
    }

    private static boolean isHoliday(LocalDate date) throws Exception {
        String response = getAPIResponse(getHolidayUrl(date));
        return parseHolidayResponse(response, date);
    }

    private static String getHolidayUrl(LocalDate date) {
        return "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo" +
                "?serviceKey=YOUR_SERVICE_KEY" +
                "&solYear=" + date.getYear() +
                "&solMonth=" + String.format("%02d", date.getMonthValue()) +
                "&_type=json";
    }

    private static String getAPIResponse(String urlString) throws Exception {
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

    private static boolean parseHolidayResponse(String response, LocalDate date) {
        // 이 부분은 JSON 파싱 라이브러리를 사용하여 구현해야 합니다. 여기서는 예시로 간단히 처리합니다.
        // 실제로는 JSON 객체를 파싱하여 날짜와 일치하는 항목이 있는지 확인해야 합니다.
        int todayInt = Integer.parseInt(date.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        return response.contains(String.valueOf(todayInt));
    }

    public static void main(String[] args) throws Exception {
        LocalDateTime offerDate = expireOfferDate();
        if (offerDate != null) {
            System.out.println("Offer expires on: " + offerDate);
        } else {
            System.out.println("Failed to calculate offer expiry date.");
        }
    }

}