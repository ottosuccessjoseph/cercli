package com.cercli.common.application;

import com.cercli.common.domain.dto.EntityResponse;
import com.cercli.common.domain.dto.ErrorResponse;
import com.cercli.common.domain.exception.DomainException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = { DomainException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public EntityResponse<ErrorResponse> handleException(DomainException exception) {
        log.error(exception.getMessage(), exception);
        return EntityResponse.<ErrorResponse>builder()
                .timestamp(ZonedDateTime.now())
                .status(com.cercli.common.domain.dto.ResponseStatus.FAILED)
                .message("Operation was not successful!")
                .errors(exception.errors)
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = { ValidationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EntityResponse handleException(ValidationException validationException) {
        log.error(validationException.getMessage(), validationException);
        ErrorResponse errorResponse;
        if (validationException instanceof ConstraintViolationException) {
            String violations = extractViolationsFromException((ConstraintViolationException) validationException);
            log.error(violations, validationException);
            errorResponse = ErrorResponse.errorResponseBuilder()
                    .field(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(violations)
                    .build();
        } else {
            String exceptionMessage = validationException.getMessage();
            errorResponse = ErrorResponse.errorResponseBuilder()
                    .field(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(exceptionMessage)
                    .build();
        }
        return EntityResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(com.cercli.common.domain.dto.ResponseStatus.FAILED)
                .errors(Arrays.asList(errorResponse))
                .build();
    }

    private String extractViolationsFromException(ConstraintViolationException validationException) {
        return validationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }
}
