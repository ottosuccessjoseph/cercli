package com.cercli.edms.domain.core.service;

import com.cercli.common.domain.dto.ErrorResponse;
import com.cercli.common.domain.exception.EmployeeDomainException;
import com.cercli.edms.domain.core.entity.Employee;
import com.cercli.edms.domain.core.event.EmployeeCreatedEvent;
import com.cercli.edms.domain.core.event.EmployeeUpdatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public class EmployeeDomainServiceImpl implements EmployeeDomainService {

    private final String NAME = "name";
    private final String ADDRESS = "address";
    private final String SALARY = "salary";
    private final String EMAIL = "email";
    private final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    @Override
    public EmployeeCreatedEvent create(Employee employee) {
        validate(employee);
        return new EmployeeCreatedEvent(employee, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public EmployeeUpdatedEvent update(Employee employee) {
        validate(employee);
        return new EmployeeUpdatedEvent(employee, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    private void validate(Employee employee) {
        List<ErrorResponse> errors = new ArrayList<>();
        boolean hasError = false;
        if (employee.getName().isEmpty()) {
            errors.add(ErrorResponse.errorResponseBuilder().field(NAME).message("Full name is required").build());
            hasError = true;
        }
        if (employee.getAddress().isEmpty()) {
            errors.add(ErrorResponse.errorResponseBuilder().field(ADDRESS).message("Address is required").build());
            hasError = true;
        }
        if (!employee.getSalary().isGreaterThanZero()) {
            errors.add(ErrorResponse.errorResponseBuilder().field(SALARY).message("Salary cannot be less than Zero").build());
            hasError = true;
        }
        if (employee.getEmail().isEmpty()) {
            errors.add(ErrorResponse.errorResponseBuilder().field(EMAIL).message("Email is required").build());
            hasError = true;
        }
        if (hasError) throw new EmployeeDomainException(errors);
        if (!Pattern.compile(EMAIL_REGEX).matcher(employee.getEmail()).matches()) {
            errors.add(ErrorResponse.errorResponseBuilder().field(EMAIL).message("Invalid email format").build());
            hasError = true;
        }
        if (hasError) throw new EmployeeDomainException(errors);
    }
}
