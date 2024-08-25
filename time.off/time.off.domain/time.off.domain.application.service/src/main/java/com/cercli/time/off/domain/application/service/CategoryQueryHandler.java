package com.cercli.time.off.domain.application.service;

import com.cercli.common.domain.exception.CategoryDomainException;
import com.cercli.time.off.domain.application.service.port.output.CategoryRepository;
import com.cercli.time.off.domain.core.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class CategoryQueryHandler {
    private final CategoryRepository categoryRepository;

    public CategoryQueryHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        Optional<List<Category>> categories = categoryRepository.getAll();
        if (categories.isEmpty()) {
            throw new CategoryDomainException("No category available at the moment!");
        }
        return categories.get();
    }
}
