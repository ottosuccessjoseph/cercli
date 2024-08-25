package com.cercli.time.off.data.access.mapper;

import com.cercli.common.domain.value.object.CategoryCode;
import com.cercli.common.domain.value.object.CategoryId;
import com.cercli.time.off.data.access.entity.CategoryEntity;
import com.cercli.time.off.domain.core.entity.Category;
import org.springframework.stereotype.Component;


/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Component
public class CategoryDataAccessMapper {

    public CategoryEntity categoryToCategoryEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.getId().getValue())
                .name(category.getName())
                .categoryCode(category.getCategoryCode())
                .build();
    }

    public Category categoryEntityToCategory(CategoryEntity categoryEntity) {
        return Category.builder()
                .id(new CategoryId(categoryEntity.getId()))
                .name(categoryEntity.getName())
                .categoryCode(categoryEntity.getCategoryCode())
                .build();
    }
}
