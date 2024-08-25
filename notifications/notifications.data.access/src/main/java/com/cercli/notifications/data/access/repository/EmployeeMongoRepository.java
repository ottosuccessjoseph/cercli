package com.cercli.notifications.data.access.repository;

import com.cercli.notifications.data.access.entity.EmployeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface EmployeeMongoRepository extends MongoRepository<EmployeeEntity, String> {
    Optional<List<EmployeeEntity>> findAllByPosition(String countryCode);
}
