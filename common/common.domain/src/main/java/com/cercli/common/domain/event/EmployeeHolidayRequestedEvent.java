package com.cercli.common.domain.event;

import com.cercli.common.domain.entity.Holiday;

import java.time.ZonedDateTime;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public class EmployeeHolidayRequestedEvent implements DomainEvent<Holiday> {

    private final Holiday holiday;
    private final ZonedDateTime createdAt;

    public EmployeeHolidayRequestedEvent(Holiday holiday, ZonedDateTime createdAt) {
        this.holiday = holiday;
        this.createdAt = createdAt;
    }

    public Holiday getHoliday() {
        return holiday;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
