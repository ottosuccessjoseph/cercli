package com.cercli.edms.domain.core.event;

import com.cercli.edms.domain.core.entity.Employee;

import java.time.ZonedDateTime;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public class EmployeeUpdatedEvent extends EmployeeEvent {

    public EmployeeUpdatedEvent(Employee employee, ZonedDateTime createdAt) {
        super(employee, createdAt);
    }
}
