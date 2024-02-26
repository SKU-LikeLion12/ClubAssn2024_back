package likelion12.puzzle.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;

import java.net.URLEncoder;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class DateService {
    

    public static LocalDateTime expireOfferDate() throws IOException{
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        now = now.plusDays(1);
        try{
            while(isHoliday(now.toLocalDate()) && now.getDayOfWeek().getValue() >= 6){
                now = now.plusDays(1);
            }
            if(now.getDayOfWeek().getValue()==5){
                now = now.with(LocalTime.of(15, 0, 0));
            }else{
                now = now.with(LocalTime.of(17, 30, 0));
            }
        }catch(UnknownContentTypeException e){
            System.out.println(e);
            return null;
        }
        return now;
    }
    private static boolean isHoliday(LocalDate today) throws IOException{
        ResponseEntity<HolidayResponse> response = getAPIResponse(getHolidayUrl(), HolidayResponse.class);

        int todayInt = Integer.parseInt(today.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        for (HolidayResponse.Item item : response.getBody().getResponse().getBody().getItems().getItem()) {
            if (item.getLocdate() == todayInt) {
                return true;
            }
        }
        return false;
    }

    private static String getHolidayUrl() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + "1Kw49paSD5t9RqepmPwQpD8cNSs0I1SzwQlnt5n62oU6vD5P%2Fxnnp2t3I8PwfAa%2BtFY02jbP6yWFcfiKPMRWqA%3D%3D");
//        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("&numOfRows=", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("solYear", "UTF-8") + "=" + URLEncoder.encode(LocalDate.now().getYear() + "", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("solMonth", "UTF-8") + "=" + URLEncoder.encode(String.format("%02d", LocalDate.now().getMonthValue()), "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        return urlBuilder.toString();
    }

    private static <T> ResponseEntity<T> getAPIResponse(String urlStr, Class<T> responseType){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-type", "application/json");
        httpHeaders.set("Accept", "application/json"); // JSON 응답을 명시적으로 요청
        HttpEntity<?> requestEntity = new HttpEntity<>(null, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().add(new MappingJackson2XmlHttpMessageConverter());

        return restTemplate.exchange(
                urlStr,
                HttpMethod.GET,
                requestEntity,
                responseType);
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class HolidayResponse {
        private Response response;
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Response {
            @Getter
            private Body body;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Body {
            @Getter
            private Items items;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Items {
            @Getter
            private List<Item> item;
        }

        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Item {
            @Getter
            private int locdate;
        }
    }


    public static void main(String[] args) throws IOException {
//        System.out.println("getAPIResponse(getHolidayUrl()).toString() = " + getAPIResponse(getHolidayUrl(),HolidayResponse.class));
//        System.out.println("isHoliday(LocalDate.now()) = " + isHoliday(LocalDate.now()));
        System.out.println("expireOfferDate() = " + expireOfferDate());
    }
}