package com.cercli.time.off.domain.application.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Getter
@Builder
@AllArgsConstructor
public class CategoryDto {
    public String id;
    public String name;
}
