package com.cercli.holidays.domain.application.service;

import com.cercli.common.domain.entity.Holiday;
import com.cercli.common.domain.event.EmployeeHolidayRequestedEvent;
import com.cercli.holidays.domain.application.service.config.RedisConfigData;
import com.cercli.holidays.domain.application.service.dto.calendarific.CalendarificHolidayResponse;
import com.cercli.holidays.domain.application.service.port.input.HolidayApplicationService;
import com.cercli.holidays.domain.application.service.port.output.EmployeeCountryCodeRepository;
import com.cercli.holidays.domain.core.entity.EmployeeCountryCode;
import com.cercli.holidays.messaging.producer.EmployeeHolidayRequestedEventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class HolidayApplicationServiceImpl implements HolidayApplicationService {

    private final RedisConfigData redisConfigData;
    private final EmployeeCountryCodeRepository employeeCountryCodeRepository;
    private final CalendarificHolidayServiceImpl calendarificHolidayService;
    private final EmployeeHolidayRequestedEventProducer employeeHolidayRequestedEventProducer;

    public HolidayApplicationServiceImpl(RedisConfigData redisConfigData,
                                         EmployeeCountryCodeRepository employeeCountryCodeRepository,
                                         CalendarificHolidayServiceImpl calendarificHolidayService,
                                         EmployeeHolidayRequestedEventProducer employeeHolidayRequestedEventProducer) {
        this.redisConfigData = redisConfigData;
        this.employeeCountryCodeRepository = employeeCountryCodeRepository;
        this.calendarificHolidayService = calendarificHolidayService;
        this.employeeHolidayRequestedEventProducer = employeeHolidayRequestedEventProducer;
    }

    @Override
    public void retrieveAndDispatch() {
        Optional<List<EmployeeCountryCode>> employeeCountryCodes = employeeCountryCodeRepository.getAll();
        if (employeeCountryCodes.isPresent() && employeeCountryCodes.get().size() > 0) {
            employeeCountryCodes.get().stream().forEach(employeeCountryCode -> {
                Mono<CalendarificHolidayResponse> holidayMono =
                        calendarificHolidayService
                                .receive(employeeCountryCode.getCountryCode(),
                                        String.valueOf(ZonedDateTime.now().getYear()));
                holidayMono.subscribe(response -> {
                    log.info("Provider returned with a success response: {}", response.toString());
                    for (CalendarificHolidayResponse.Holiday holiday: response.getResponse().getHolidays()) {
                        // Filter holidays that falls within next Seven(7) days
                        LocalDate today = LocalDate.now();
                        LocalDate nextWeek = today.plusDays(7);

                        LocalDate holidayDate = parseToLocalDate(holiday.getDate().getIso());
//                        if (holidayDate.isBefore(today) && holidayDate.isBefore(nextWeek)) {
//                            log.warn("Holiday is not within next 7 days!");
//                            continue;
//                        }
                        employeeHolidayRequestedEventProducer
                                .send(redisConfigData.getStreams().getRequest().getEmployeeHolidayRequested(),
                                        new EmployeeHolidayRequestedEvent(Holiday.builder()
                                                .name(holiday.getName())
                                                .description(holiday.getDescription())
                                                .date(holiday.getDate().getIso())
                                                .countryCode(holiday.getCountry().getId())
                                                .country(holiday.getCountry().getName())
                                                .build(), ZonedDateTime.now())).subscribe();
                    }
                }, error -> {
                    log.error("Provider returned with an error: {}", error.getMessage());
                }, () -> {
                    log.info("Request to provider completed!");
                });
            });
        }
    }

    public static LocalDate parseToLocalDate(String dateString) {
        try {
            if (dateString.contains("T")) {
                // String contains time and possibly offset, parse using OffsetDateTime
                OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                return offsetDateTime.toLocalDate();
            } else {
                // String is just a simple date, parse using LocalDate
                return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
            }
        } catch (DateTimeParseException e) {
            System.err.println("Failed to parse date: " + dateString);
            e.printStackTrace();
            return null;  // or handle the error as needed
        }
    }
}
