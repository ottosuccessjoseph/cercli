package com.cercli.edms.domain.application.service;

import com.cercli.common.domain.dto.ErrorResponse;
import com.cercli.common.domain.exception.EmployeeDomainException;
import com.cercli.edms.domain.application.service.port.output.EmployeeRepository;
import com.cercli.edms.domain.core.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class EmployeeCreateHelper {

    private final String EMAIL = "email";
    private final EmployeeRepository employeeRepository;

    public EmployeeCreateHelper(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee persistEmployee(Employee employee) {
        List<ErrorResponse> errors = new ArrayList<>();
        Optional<Employee> existingEmployeeEmail = employeeRepository.findByEmail(employee.getEmail());
        existingEmployeeEmail.ifPresent(emp ->
                errors.add(ErrorResponse.errorResponseBuilder()
                        .field(EMAIL)
                        .message("Email is already taken!")
                        .build())
        );
        if (errors.size() > 0) throw new EmployeeDomainException(errors);
        return save(employee);
    }

    private Employee save(Employee employee) {
        Employee employeeResult = employeeRepository.save(employee);
        if (null == employeeResult) {
            throw new EmployeeDomainException("An error occurred while attempting to create an account!");
        }
        log.info("Employee with id {} was successfully created.", employee.getId().getValue());
        return employeeResult;
    }
}
