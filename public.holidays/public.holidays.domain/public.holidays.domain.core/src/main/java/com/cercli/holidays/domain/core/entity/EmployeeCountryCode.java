package com.cercli.holidays.domain.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeeCountryCode {
    private String countryCode;
}
