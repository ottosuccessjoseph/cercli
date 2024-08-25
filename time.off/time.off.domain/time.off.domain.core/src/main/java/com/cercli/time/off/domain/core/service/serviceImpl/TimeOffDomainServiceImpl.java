package com.cercli.time.off.domain.core.service.serviceImpl;

import com.cercli.time.off.domain.core.entity.TimeOffRequest;
import com.cercli.time.off.domain.core.event.TimeOffRequestedEvent;
import com.cercli.time.off.domain.core.service.TimeOffDomainService;

import java.time.ZonedDateTime;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public class TimeOffDomainServiceImpl implements TimeOffDomainService {

    @Override
    public TimeOffRequestedEvent validate(TimeOffRequest timeOffRequest) {
        return new TimeOffRequestedEvent(timeOffRequest, ZonedDateTime.now());
    }
}
