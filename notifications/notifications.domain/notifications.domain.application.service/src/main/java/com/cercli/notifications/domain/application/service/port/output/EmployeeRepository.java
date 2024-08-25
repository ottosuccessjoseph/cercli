package com.cercli.notifications.domain.application.service.port.output;

import com.cercli.notifications.domain.core.entity.Employee;

import java.util.List;
import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface EmployeeRepository {
    Optional<List<Employee>> findAllByPosition(String countryCode);
}
