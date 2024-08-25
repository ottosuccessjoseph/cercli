package com.cercli.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Success.otto
 * @since 0.0.1
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntityResponse<T extends EntityResponse> {
    private ZonedDateTime timestamp;
    private ResponseStatus status;
    private String message;
    private T data;
    private List<T> errors;
}
