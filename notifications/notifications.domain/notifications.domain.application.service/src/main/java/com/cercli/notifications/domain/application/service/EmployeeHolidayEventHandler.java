package com.cercli.notifications.domain.application.service;

import com.cercli.common.domain.entity.Holiday;
import com.cercli.notifications.domain.application.service.port.output.EmployeeRepository;
import com.cercli.notifications.domain.application.service.port.output.NotificationService;
import com.cercli.notifications.domain.core.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class EmployeeHolidayEventHandler {

    private final NotificationService notificationService;
    private final EmployeeRepository employeeRepository;

    public EmployeeHolidayEventHandler(NotificationService notificationService,
                                       EmployeeRepository employeeRepository) {
        this.notificationService = notificationService;
        this.employeeRepository = employeeRepository;
    }

    public void handle(Holiday holiday) {
        Optional<List<Employee>> employees = employeeRepository.findAllByPosition(holiday.getCountryCode().toUpperCase());
        if (employees.isPresent() && employees.get().size() > 0) {
            for (Employee employee: employees.get()) {
                notificationService.send(employee.getEmail(), holiday.getName(), holiday.getDescription());
            }
        }
    }
}
