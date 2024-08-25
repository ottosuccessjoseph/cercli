package com.cercli.edms.domain.application.service;

import com.cercli.edms.domain.application.service.dto.command.CreateEmployeeCommand;
import com.cercli.edms.domain.application.service.dto.response.EmployeeDto;
import com.cercli.edms.domain.application.service.mapper.EmployeeDataMapper;
import com.cercli.edms.domain.core.entity.Employee;
import com.cercli.edms.domain.core.event.EmployeeCreatedEvent;
import com.cercli.edms.domain.core.service.EmployeeDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class EmployeeCreateCommandHandler {

    private final EmployeeDataMapper employeeDataMapper;
    private final EmployeeCreateHelper employeeCreateHelper;
    private final EmployeeDomainService employeeDomainService;

    public EmployeeCreateCommandHandler(EmployeeDataMapper employeeDataMapper,
                                        EmployeeCreateHelper employeeCreateHelper,
                                        EmployeeDomainService employeeDomainService) {
        this.employeeDataMapper = employeeDataMapper;
        this.employeeCreateHelper = employeeCreateHelper;
        this.employeeDomainService = employeeDomainService;
    }

    public Employee commandHandler(CreateEmployeeCommand command) {
        EmployeeCreatedEvent employeeCreatedEvent = employeeDomainService
                .create(employeeDataMapper.createEmployeeCommandToEmployee(command));
        //Emit an event to consumers that a user has been created
        return employeeCreateHelper.persistEmployee(employeeCreatedEvent.getEmployee());
    }
}
