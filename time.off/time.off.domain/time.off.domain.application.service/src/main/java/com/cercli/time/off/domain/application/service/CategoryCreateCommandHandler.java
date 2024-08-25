package com.cercli.time.off.domain.application.service;

import com.cercli.common.domain.exception.CategoryDomainException;
import com.cercli.common.domain.value.object.CategoryCode;
import com.cercli.time.off.domain.application.service.dto.command.CategoryCommand;
import com.cercli.time.off.domain.application.service.mapper.CategoryDataMapper;
import com.cercli.time.off.domain.application.service.port.output.CategoryRepository;
import com.cercli.time.off.domain.core.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class CategoryCreateCommandHandler {
    private final CategoryRepository categoryRepository;
    private final CategoryDataMapper categoryDataMapper;

    public CategoryCreateCommandHandler(CategoryRepository categoryRepository,
                                        CategoryDataMapper categoryDataMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryDataMapper = categoryDataMapper;
    }

    public Category commandHandler(CategoryCommand command) {
        Optional<Category> category = categoryRepository.findByCode(CategoryCode.valueOf(command.getCode()));
        if (category.isEmpty()) {
            return categoryRepository.save(categoryDataMapper.categoryCommandToCategory(command));
        }
        return null;
    }
}
