package com.cercli.notifications.domain.application.service;

import com.cercli.common.domain.event.DomainEvent;
import com.cercli.common.domain.event.EmployeeHolidayRequestedEvent;
import com.cercli.notifications.domain.application.service.port.input.NotificationApplicationService;
import org.springframework.stereotype.Component;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Component
public class NotificationApplicationServiceImpl implements NotificationApplicationService {

    private final EmployeeHolidayEventHandler employeeHolidayEventHandler;

    public NotificationApplicationServiceImpl(EmployeeHolidayEventHandler employeeHolidayEventHandler) {
        this.employeeHolidayEventHandler = employeeHolidayEventHandler;
    }

    @Override
    public void eventHandler(DomainEvent event) {
        if (event instanceof EmployeeHolidayRequestedEvent) {
            employeeHolidayEventHandler.handle(((EmployeeHolidayRequestedEvent) event).getHoliday());
        }
    }
}
