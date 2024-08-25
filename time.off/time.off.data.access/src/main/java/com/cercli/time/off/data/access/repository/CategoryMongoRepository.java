package com.cercli.time.off.data.access.repository;

import com.cercli.common.domain.value.object.CategoryCode;
import com.cercli.time.off.data.access.entity.CategoryEntity;
import com.cercli.time.off.domain.core.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface CategoryMongoRepository extends MongoRepository<CategoryEntity, String> {
    Optional<CategoryEntity> findByCategoryCode(CategoryCode categoryCode);
}
