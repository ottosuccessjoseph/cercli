package com.cercli.time.off.domain.core.service;

import com.cercli.time.off.domain.core.entity.TimeOffRequest;
import com.cercli.time.off.domain.core.event.TimeOffRequestedEvent;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface TimeOffDomainService {
    public TimeOffRequestedEvent validate(TimeOffRequest timeOffRequest);
}
