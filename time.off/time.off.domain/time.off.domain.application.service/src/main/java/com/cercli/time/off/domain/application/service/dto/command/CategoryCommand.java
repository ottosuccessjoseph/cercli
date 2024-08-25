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
public class CategoryCommand {

    @NotNull(message = "Category name is required")
    private final String name;
    @NotNull(message = "Category code is required")
    private final String code;
}
