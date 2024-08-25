package com.cercli.common.domain.exception;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public class TimeOffRequestDomainException extends DomainException {
    public TimeOffRequestDomainException(String message) {
        super(message);
    }

    public TimeOffRequestDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
