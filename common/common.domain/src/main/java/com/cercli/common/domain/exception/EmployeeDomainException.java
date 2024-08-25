package com.cercli.common.domain.exception;

import com.cercli.common.domain.dto.ErrorResponse;

import java.util.List;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public class EmployeeDomainException extends DomainException {

    public EmployeeDomainException(String message) {
        super(message);
    }

    public EmployeeDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeDomainException(List<ErrorResponse> errors) {
        super(errors);
    }
}
