package com.cercli.holidays.domain.application.service.scheduler;

import com.cercli.holidays.domain.application.service.port.input.HolidayApplicationService;
import com.cercli.infrastructure.outbox.OutboxScheduler;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class HolidayScheduler implements OutboxScheduler {

    private final HolidayApplicationService holidayApplicationService;

    public HolidayScheduler(HolidayApplicationService holidayApplicationService) {
        this.holidayApplicationService = holidayApplicationService;
    }

    @Override
    @PostConstruct
//    @Scheduled(cron = "@midnight")
    public void processOutboxMessage() {
        log.info("Holiday scheduler started!!!");
        holidayApplicationService.retrieveAndDispatch();
    }
}
