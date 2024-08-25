package com.cercli.time.off.domain.application.service.port.output;

import com.cercli.common.domain.value.object.CategoryCode;
import com.cercli.common.domain.value.object.CategoryId;
import com.cercli.time.off.domain.core.entity.Category;

import java.util.List;
import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface CategoryRepository {
    Optional<List<Category>> getAll();
    Optional<Category> findById(CategoryId id);
    Optional<Category> findByCode(CategoryCode categoryCode);
    Category save(Category category);
}
