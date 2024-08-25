package com.cercli.holidays.domain.application.service.port.input;

import com.cercli.common.domain.entity.Holiday;
import reactor.core.publisher.Mono;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface HolidayService<T extends Holiday> {
    Mono<T> receive(String countryCode, String year);
}
