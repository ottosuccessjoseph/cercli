package com.cercli.time.off.data.access.adapter;

import com.cercli.common.domain.value.object.CategoryCode;
import com.cercli.common.domain.value.object.CategoryId;
import com.cercli.time.off.data.access.mapper.CategoryDataAccessMapper;
import com.cercli.time.off.data.access.repository.CategoryMongoRepository;
import com.cercli.time.off.domain.application.service.port.output.CategoryRepository;
import com.cercli.time.off.domain.core.entity.Category;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Component
@EnableCaching
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryDataAccessMapper categoryDataAccessMapper;
    private final CategoryMongoRepository categoryMongoRepository;

    public CategoryRepositoryImpl(CategoryDataAccessMapper categoryDataAccessMapper,
                                  CategoryMongoRepository categoryMongoRepository) {
        this.categoryDataAccessMapper = categoryDataAccessMapper;
        this.categoryMongoRepository = categoryMongoRepository;
    }

    @Override
    public Optional<List<Category>> getAll() {
        return Optional.of(categoryMongoRepository.findAll().stream().map(categoryDataAccessMapper::categoryEntityToCategory)
                .collect(Collectors.toList()));
    }

    @Override
    @Cacheable(value = "categories", key = "#id.value", unless="#result == null")
    public Optional<Category> findById(CategoryId id) {
        return categoryMongoRepository
                .findById(id.getValue())
                .map(categoryDataAccessMapper::categoryEntityToCategory);
    }

    @Override
    public Optional<Category> findByCode(CategoryCode categoryCode) {
        return categoryMongoRepository
                .findByCategoryCode(categoryCode)
                .map(categoryDataAccessMapper::categoryEntityToCategory);
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = "categories", key = "#category.id.value")
            }
    )
    public Category save(Category category) {
        return categoryDataAccessMapper
                .categoryEntityToCategory(categoryMongoRepository
                        .save(categoryDataAccessMapper
                                .categoryToCategoryEntity(category)));
    }


}
