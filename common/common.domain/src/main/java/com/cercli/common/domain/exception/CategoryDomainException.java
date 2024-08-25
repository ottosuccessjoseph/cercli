package com.cercli.common.domain.exception;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public class CategoryDomainException extends DomainException {
    public CategoryDomainException(String message) {
        super(message);
    }

    public CategoryDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
