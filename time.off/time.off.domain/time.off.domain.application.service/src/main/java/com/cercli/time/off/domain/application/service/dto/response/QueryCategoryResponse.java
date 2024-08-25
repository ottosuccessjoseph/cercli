package com.cercli.time.off.domain.application.service.dto.response;

import com.cercli.common.domain.dto.EntityResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Getter
@Builder(builderMethodName = "queryCategoryResponseBuilder")
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryCategoryResponse extends EntityResponse {
    public List<CategoryDto> categories;
}
