package com.cercli.edms.domain.application.service;

import com.cercli.common.domain.exception.EmployeeDomainException;
import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.edms.domain.application.service.dto.response.EmployeeDto;
import com.cercli.edms.domain.application.service.port.output.EmployeeRepository;
import com.cercli.edms.domain.core.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class EmployeeQueryHandler {

    private final EmployeeRepository employeeRepository;

    public EmployeeQueryHandler(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto getById(String employeeId) {
        Optional<Employee> employee = employeeRepository.getById(new EmployeeId(employeeId));
        if (employee.isEmpty()) {
            throw new EmployeeDomainException("Employee does not exist!");
        }
        return employee.map(emp -> EmployeeDto.builder()
                .id(emp.getId().getValue())
                .name(emp.getName())
                .position(emp.getPosition())
                .email(emp.getEmail())
                .salary(emp.getSalary().getAmount())
                .address(emp.getAddress())
                .createdAt(emp.getCreatedAt())
                .updatedAt(emp.getUpdatedAt())
                .build()).get();
    }

    public List<EmployeeDto> getAll() {
        Optional<List<Employee>> employees = employeeRepository.getAll();
        if (employees.isEmpty() || employees.get().size() == 0) {
            throw new EmployeeDomainException("No record found!");
        }
        return employees.get().stream().map(emp -> EmployeeDto.builder()
                .id(emp.getId().getValue())
                .name(emp.getName())
                .position(emp.getPosition())
                .email(emp.getEmail())
                .salary(emp.getSalary().getAmount())
                .address(emp.getAddress())
                .createdAt(emp.getCreatedAt())
                .updatedAt(emp.getUpdatedAt())
                .build()
        ).collect(Collectors.toList());
    }
}
