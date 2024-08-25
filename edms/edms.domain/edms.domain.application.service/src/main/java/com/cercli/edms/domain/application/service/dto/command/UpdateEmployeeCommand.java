package com.cercli.edms.domain.application.service.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Getter
@SuperBuilder
@AllArgsConstructor
@ToString
public class UpdateEmployeeCommand {

    @JsonProperty("full_name")
    @NotNull(message = "Full name is required")
    private final String name;
    @JsonProperty("position")
    @NotNull(message = "Employee Position required")
    private final String position;
    @JsonProperty("email")
    @NotNull(message = "Email is required")
    private final String email;
    @JsonProperty("salary")
    @NotNull(message = "Salary is required")
    private final String salary;
    @JsonProperty("address")
    @NotNull(message = "Address is required")
    private final String address;
}
