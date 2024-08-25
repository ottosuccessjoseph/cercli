package com.cercli.edms.domain.application.service.port.output;

import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.edms.domain.core.entity.Employee;

import java.util.List;
import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface EmployeeRepository {
    Optional<Employee> getById(EmployeeId employeeId);
    Optional<List<Employee>> getAll();
    Employee save(Employee employee);
    Employee update(Employee employee);
    Optional<Employee> findByEmail(String email);
}
