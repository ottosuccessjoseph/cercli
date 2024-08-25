package com.cercli.edms.domain.application.service.dto.response;

import com.cercli.common.domain.dto.EntityResponse;
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
@Builder(builderMethodName = "queryEmployeeResponseBuilder")
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateEmployeeResponse extends EntityResponse {
    public String id;
}
