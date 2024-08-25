package com.cercli.time.off.domain.application.service.dto.command;

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
public class CreateTimeOffCommand {

    @JsonProperty("category_id")
//    @NotNull(message = "Category is required")
    private final String categoryId;
    @JsonProperty("employee_id")
    @NotNull(message = "Employee is required")
    private final String employeeId;
    @JsonProperty("start_date")
    @NotNull(message = "Start date is required")
    private final String startDate;
    @JsonProperty("end_date")
    @NotNull(message = "End date is required")
    private final String endDate;
}
