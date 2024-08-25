package com.cercli.edms.domain.application.service.mapper;

import com.cercli.common.domain.DateHelper;
import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.common.domain.value.object.Money;
import com.cercli.edms.domain.application.service.dto.command.CreateEmployeeCommand;
import com.cercli.edms.domain.application.service.dto.command.UpdateEmployeeCommand;
import com.cercli.edms.domain.core.entity.Employee;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Component
public class EmployeeDataMapper {

    public Employee createEmployeeCommandToEmployee(CreateEmployeeCommand command) {
        return Employee.builder()
                .id(new EmployeeId(UUID.randomUUID().toString()))
                .name(command.getName())
                .address(command.getAddress())
                .position(command.getPosition())
                .email(command.getEmail())
                .salary(new Money(new BigDecimal(command.getSalary())))
                .createdAt(ZonedDateTime.now(ZoneId.of(DateHelper.TIME_ZONE_AE)))
                .updatedAt(ZonedDateTime.now(ZoneId.of(DateHelper.TIME_ZONE_AE)))
                .build();
    }

    public Employee updateEmployeeCommandToEmployee(UpdateEmployeeCommand command) {
        return Employee.builder()
                .id(new EmployeeId(UUID.randomUUID().toString()))
                .name(command.getName())
                .address(command.getAddress())
                .email(command.getEmail())
                .position(command.getPosition())
                .salary(new Money(new BigDecimal(command.getSalary())))
                .build();
    }
}
