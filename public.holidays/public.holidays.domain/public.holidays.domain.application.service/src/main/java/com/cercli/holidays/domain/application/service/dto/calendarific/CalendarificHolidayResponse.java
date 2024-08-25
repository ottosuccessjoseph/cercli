package com.cercli.holidays.domain.application.service.dto.calendarific;

import com.cercli.common.domain.entity.Holiday;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Getter
@Setter
@ToString()
public class CalendarificHolidayResponse extends Holiday {

    private Meta meta;
    private Response response;

    @Getter
    @Setter
    public static class Meta {
        private String code;
    }

    @Getter
    @Setter
    public static class Response {
        private List<Holiday> holidays;
    }

    @Getter
    @Setter
    public static class Holiday {
        @JsonProperty("name")
        private String name;
        @JsonProperty("description")
        private String description;
        @JsonProperty("date")
        private Date date;
        @JsonProperty("country")
        private Country country;
        @JsonProperty("type")
        private List<String> types;
        @JsonProperty("primary_type")
        private String primaryType;
        @JsonProperty("canonical_url")
        private String canonicalUrl;
        @JsonProperty("urlid")
        private String urlId;
        @JsonProperty("locations")
        private String locations;
        @JsonProperty("states")
        private String states;
    }

    @Getter
    @Setter
    public static class Country {
        @JsonProperty("id")
        private String id;
        @JsonProperty("name")
        private String name;
    }

    @Getter
    @Setter
    public static class Date {
        @JsonProperty("iso")
        private String iso;
        @JsonProperty("datetime")
        private DateTime dateTime;
    }

    @Getter
    @Setter
    public static class DateTime {
        @JsonProperty("year")
        private int year;
        @JsonProperty("month")
        private int month;
        @JsonProperty("day")
        private int day;
    }

}
