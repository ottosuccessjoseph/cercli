package com.cercli.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Getter
@Builder(builderMethodName = "errorResponseBuilder")
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse extends EntityResponse {
    private String field;
    private String message;
}
