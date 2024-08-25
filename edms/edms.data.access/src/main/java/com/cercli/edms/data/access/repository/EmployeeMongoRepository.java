package com.cercli.edms.data.access.repository;

import com.cercli.edms.data.access.entity.EmployeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface EmployeeMongoRepository extends MongoRepository<EmployeeEntity, String> {
    Optional<EmployeeEntity> findByEmail(String email);
}
