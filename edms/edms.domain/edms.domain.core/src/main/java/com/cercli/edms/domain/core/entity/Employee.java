package com.cercli.edms.domain.core.entity;

import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.common.domain.value.object.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Employee implements Serializable {
    private EmployeeId id;
    private String name;
    private String position;
    private String email;
    private Money salary;
    private String address;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
