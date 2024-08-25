package com.cercli.holidays.domain.application.service.port.output;

import com.cercli.holidays.domain.core.entity.EmployeeCountryCode;

import java.util.List;
import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface EmployeeCountryCodeRepository {
    public Optional<List<EmployeeCountryCode>> getAll();
}
