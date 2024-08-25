package com.cercli.notifications.data.access.mapper;

import com.cercli.common.domain.DateHelper;
import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.notifications.data.access.entity.EmployeeEntity;
import com.cercli.notifications.domain.core.entity.Employee;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Component
public class EmployeeDataAccessMapper {

    public EmployeeEntity employeeToEmployeeEntity(Employee employee) {
        return EmployeeEntity.builder()
                .id(employee.getId().getValue())
                .name(employee.getName())
                .address(employee.getAddress())
                .email(employee.getEmail())
                .position(employee.getPosition())
                .createdAt(employee.getCreatedAt().toInstant()) // Converts to UTC
                .updatedAt(employee.getUpdatedAt().toInstant()) // Converts to UTC
                .build();
    }

    public Employee employeeEntityToEmployee(EmployeeEntity employeeEntity) {
        return Employee.builder()
                .id(new EmployeeId(employeeEntity.getId()))
                .name(employeeEntity.getName())
                .address(employeeEntity.getAddress())
                .email(employeeEntity.getEmail())
                .position(employeeEntity.getPosition())
                .createdAt(employeeEntity.getCreatedAt().atZone(ZoneId.of(DateHelper.TIME_ZONE_AE))) // Converts back to AE
                .updatedAt(employeeEntity.getUpdatedAt().atZone(ZoneId.of(DateHelper.TIME_ZONE_AE))) // Converts back to AE
                .build();
    }
}
