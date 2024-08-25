package com.cercli.edms.domain.core.event;

import com.cercli.common.domain.event.DomainEvent;
import com.cercli.edms.domain.core.entity.Employee;

import java.time.ZonedDateTime;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public abstract class EmployeeEvent implements DomainEvent<Employee> {
    private final Employee employee;
    private final ZonedDateTime createdAt;

    protected EmployeeEvent(Employee employee, ZonedDateTime createdAt) {
        this.employee = employee;
        this.createdAt = createdAt;
    }

    public Employee getEmployee() {
        return employee;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
