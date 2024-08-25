package com.cercli.common.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public class DateHelper {

    public final static String PATTERN = "uuuu-MM-dd";
    public final static String TIME_ZONE = "UTC";
    public final static String TIME_ZONE_AE = "Asia/Dubai";

    public static ZonedDateTime convert(String dateToFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

        // Attempt to parse as LocalDateTime if the pattern includes time
        try {
            LocalDate localDate = LocalDate.parse(dateToFormat, formatter);
            return localDate.atStartOfDay(ZoneId.of(TIME_ZONE));
        } catch (DateTimeParseException e) {
            // If parse as LocalDateTime fails, try LocalDate if only a date is provided
            LocalDate localDate = LocalDate.parse(dateToFormat, formatter);
            return localDate.atStartOfDay(ZoneId.of(TIME_ZONE));
        }
    }

    public static ZonedDateTime convert(String dateToFormat, String zoneId) {
        return LocalDateTime.parse(dateToFormat,
                DateTimeFormatter
                        .ofPattern(PATTERN)).atZone(ZoneId.of(zoneId));
    }
}
