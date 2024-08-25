package com.cercli.holidays.domain.application.service;

import com.cercli.common.domain.entity.Holiday;
import com.cercli.holidays.domain.application.service.dto.calendarific.CalendarificHolidayResponse;
import com.cercli.holidays.domain.application.service.port.input.HolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class CalendarificHolidayServiceImpl implements HolidayService<CalendarificHolidayResponse> {

    private final String URL; // = "https://calendarific.com/api/v2/holidays";
    private final String API_KEY;

    public CalendarificHolidayServiceImpl(@Value("${providers.calendarific.url}") String url,
                                          @Value("${providers.calendarific.api_key}") String api_key) {
        this.URL = url;
        this.API_KEY = api_key;
    }

    @Override
    public Mono<CalendarificHolidayResponse> receive(String countryCode, String year) {
        return getHolidays(countryCode, year);
    }

    private Mono<CalendarificHolidayResponse> getHolidays(String countryCode, String year) {
//        String url = "https://calendarific.com/api/v2/holidays?api_key=L1jLNEjCtQIH00z9FuIjJGdoA2iyzn9H&country=AE&year=2024";
        WebClient webClient = WebClient.builder()
                .baseUrl(URL + "?api_key=" + API_KEY + "&country=" + countryCode + "&year=" + year)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        Mono<CalendarificHolidayResponse> responseMono = webClient.get().retrieve().bodyToMono(CalendarificHolidayResponse.class);
        return responseMono;
    }
}
