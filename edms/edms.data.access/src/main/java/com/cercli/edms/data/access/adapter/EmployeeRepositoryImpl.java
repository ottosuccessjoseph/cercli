package com.cercli.edms.data.access.adapter;

import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.edms.data.access.mapper.EmployeeDataAccessMapper;
import com.cercli.edms.data.access.repository.EmployeeMongoRepository;
import com.cercli.edms.domain.application.service.port.output.EmployeeRepository;
import com.cercli.edms.domain.core.entity.Employee;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @Cacheable(value = "employees", key = "#employeeId.value", unless="#result == null")
    public Optional<Employee> getById(EmployeeId employeeId) {
        return employeeMongoRepository
                .findById(employeeId.getValue())
                .map(employeeDataAccessMapper::employeeEntityToEmployee);
    }

    @Override
    public Optional<List<Employee>> getAll() {
        return Optional.of(employeeMongoRepository.findAll().
                stream().map(employeeDataAccessMapper::employeeEntityToEmployee)
                .collect(Collectors.toList()));
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = "employees", key = "#employee.id.value"),
                    @CachePut(value = "employees", key = "#employee.email.value")
            }
    )
    public Employee save(Employee employee) {
        return employeeDataAccessMapper
                .employeeEntityToEmployee(employeeMongoRepository
                        .save(employeeDataAccessMapper
                                .employeeToEmployeeEntity(employee)));
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = "employees", key = "#employee.id.value"),
                    @CachePut(value = "employees", key = "#employee.email.value")
            }
    )
    public Employee update(Employee employee) {
        return employeeDataAccessMapper
                .employeeEntityToEmployee(employeeMongoRepository
                        .save(employeeDataAccessMapper
                                .employeeToEmployeeEntity(employee)));
    }

    @Override
    @Cacheable(value = "employees", key = "#email", unless="#result == null")
    public Optional<Employee> findByEmail(String email) {
        return employeeMongoRepository
                .findByEmail(email)
                .map(employeeDataAccessMapper::employeeEntityToEmployee);
    }
}
