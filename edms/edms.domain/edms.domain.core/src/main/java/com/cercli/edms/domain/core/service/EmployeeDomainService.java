package com.cercli.edms.domain.core.service;

import com.cercli.edms.domain.core.entity.Employee;
import com.cercli.edms.domain.core.event.EmployeeCreatedEvent;
import com.cercli.edms.domain.core.event.EmployeeUpdatedEvent;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface EmployeeDomainService {
    EmployeeCreatedEvent create(Employee employee);
    EmployeeUpdatedEvent update(Employee employee);
}
