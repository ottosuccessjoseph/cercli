package com.cercli.common.domain.exception;

import com.cercli.common.domain.dto.ErrorResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public class DomainException extends RuntimeException {

    public List<ErrorResponse> errors = new ArrayList<>();

    public DomainException(String message) {
        super(message);
    }

    public DomainException(List<ErrorResponse> errors) {
        this.errors = errors;
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
