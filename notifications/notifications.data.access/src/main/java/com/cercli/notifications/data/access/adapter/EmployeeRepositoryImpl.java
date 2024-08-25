package com.cercli.notifications.data.access.adapter;

import com.cercli.notifications.data.access.mapper.EmployeeDataAccessMapper;
import com.cercli.notifications.data.access.repository.EmployeeMongoRepository;
import com.cercli.notifications.domain.application.service.port.output.EmployeeRepository;
import com.cercli.notifications.domain.core.entity.Employee;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeDataAccessMapper employeeDataAccessMapper;
    private final EmployeeMongoRepository employeeMongoRepository;

    public EmployeeRepositoryImpl(EmployeeDataAccessMapper employeeDataAccessMapper,
                                  EmployeeMongoRepository employeeMongoRepository) {
        this.employeeDataAccessMapper = employeeDataAccessMapper;
        this.employeeMongoRepository = employeeMongoRepository;
    }

    @Override
    @Cacheable(value = "employees", key = "#countryCode", unless="#result == null")
    public Optional<List<Employee>> findAllByPosition(String countryCode) {
        return Optional.of(employeeMongoRepository
                .findAllByPosition(countryCode).get().stream()
                .map(employeeDataAccessMapper::employeeEntityToEmployee).collect(Collectors.toList()));
    }
}
