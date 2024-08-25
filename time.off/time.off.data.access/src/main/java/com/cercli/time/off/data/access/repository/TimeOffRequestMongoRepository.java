package com.cercli.time.off.data.access.repository;

import com.cercli.time.off.data.access.entity.TimeOffRequestEntity;
import com.cercli.time.off.domain.core.entity.TimeOffRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Repository
public interface TimeOffRequestMongoRepository extends MongoRepository<TimeOffRequestEntity, UUID> {
    Optional<List<TimeOffRequestEntity>> findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(String value,
                                                                                                             Instant toInstant,
                                                                                                             Instant toInstant1);
}
