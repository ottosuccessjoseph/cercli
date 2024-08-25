package com.cercli.holidays.data.access.adapter;

import com.cercli.holidays.domain.application.service.port.output.EmployeeCountryCodeRepository;
import com.cercli.holidays.domain.core.entity.EmployeeCountryCode;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Component
public class EmployeeCountryCodeRepositoryImpl implements EmployeeCountryCodeRepository {
    @Override
    public Optional<List<EmployeeCountryCode>> getAll() {
        return Optional.of(Arrays.asList(EmployeeCountryCode.builder()
                        .countryCode("AE")
                .build()));
    }
}
