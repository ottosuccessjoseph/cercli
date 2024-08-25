package com.cercli.edms.domain.core.event;

import com.cercli.edms.domain.core.entity.Employee;

import java.time.ZonedDateTime;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public class EmployeeCreatedEvent extends EmployeeEvent {

    public EmployeeCreatedEvent(Employee employee, ZonedDateTime createdAt) {
        super(employee, createdAt);
    }
}
