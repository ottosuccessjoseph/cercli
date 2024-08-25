package com.cercli.edms.domain.application.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Getter
@Builder
@AllArgsConstructor
public class EmployeeDto {
    private String id;
    private String name;
    private String position;
    private String email;
    private BigDecimal salary;
    private String address;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
