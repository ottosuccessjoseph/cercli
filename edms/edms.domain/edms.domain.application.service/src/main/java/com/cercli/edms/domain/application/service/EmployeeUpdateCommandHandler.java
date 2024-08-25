package com.cercli.edms.domain.application.service;

import com.cercli.common.domain.DateHelper;
import com.cercli.common.domain.exception.EmployeeDomainException;
import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.edms.domain.application.service.dto.command.UpdateEmployeeCommand;
import com.cercli.edms.domain.application.service.mapper.EmployeeDataMapper;
import com.cercli.edms.domain.application.service.port.output.EmployeeRepository;
import com.cercli.edms.domain.core.entity.Employee;
import com.cercli.edms.domain.core.event.EmployeeUpdatedEvent;
import com.cercli.edms.domain.core.service.EmployeeDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class EmployeeUpdateCommandHandler {

    private final EmployeeDataMapper employeeDataMapper;
    private final EmployeeDomainService employeeDomainService;
    private final EmployeeRepository employeeRepository;

    public EmployeeUpdateCommandHandler(EmployeeDataMapper employeeDataMapper,
                                        EmployeeDomainService employeeDomainService,
                                        EmployeeRepository employeeRepository) {
        this.employeeDataMapper = employeeDataMapper;
        this.employeeDomainService = employeeDomainService;
        this.employeeRepository = employeeRepository;
    }

    public Employee commandHandler(String employeeId, UpdateEmployeeCommand command) {
        Optional<Employee> employee = employeeRepository.getById(new EmployeeId(employeeId));
        if (employee.isEmpty()) {
            throw new EmployeeDomainException("Invalid employee.");
        }
        // Check if employee email exist which doesn't have the employee id if you want to update the email
        // To keep it simple, i won't update the email
        EmployeeUpdatedEvent employeeUpdatedEvent = employeeDomainService
                .update(employeeDataMapper.updateEmployeeCommandToEmployee(command));
        Employee updatedEmployee = employeeUpdatedEvent.getEmployee();
        updatedEmployee.setId(employee.get().getId());
        updatedEmployee.setCreatedAt(employee.get().getCreatedAt());
        updatedEmployee.setUpdatedAt(ZonedDateTime.now(ZoneId.of(DateHelper.TIME_ZONE_AE)));
        return save(updatedEmployee);
    }

    private Employee save(Employee employee) {
        Employee employeeResult = employeeRepository.save(employee);
        if (null == employeeResult) {
            throw new EmployeeDomainException("An error occurred while attempting to create employee account!");
        }
        log.info("Employee with id {} was successfully updated", employeeResult.getId().getValue());
        return employeeResult;
    }
}
