package com.cercli.time.off.domain.core.event;

import com.cercli.common.domain.event.DomainEvent;
import com.cercli.time.off.domain.core.entity.TimeOffRequest;

import java.time.ZonedDateTime;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public class TimeOffRequestedEvent implements DomainEvent<TimeOffRequest> {

    private final TimeOffRequest timeOffRequest;
    private final ZonedDateTime createdAt;

    public TimeOffRequestedEvent(TimeOffRequest timeOffRequest, ZonedDateTime createdAt) {
        this.timeOffRequest = timeOffRequest;
        this.createdAt = createdAt;
    }

    public TimeOffRequest getTimeOffRequest() {
        return timeOffRequest;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
