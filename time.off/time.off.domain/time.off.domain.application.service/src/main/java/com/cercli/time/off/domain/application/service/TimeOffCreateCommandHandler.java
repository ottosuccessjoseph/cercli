package com.cercli.time.off.domain.application.service;

import com.cercli.common.domain.exception.CategoryDomainException;
import com.cercli.common.domain.value.object.CategoryId;
import com.cercli.time.off.domain.application.service.dto.command.CreateTimeOffCommand;
import com.cercli.time.off.domain.application.service.mapper.TimeOffDataMapper;
import com.cercli.time.off.domain.application.service.port.output.CategoryRepository;
import com.cercli.time.off.domain.core.entity.Category;
import com.cercli.time.off.domain.core.entity.TimeOffRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class TimeOffCreateCommandHandler {

    private final CategoryRepository categoryRepository;
    private final TimeOffCreateHelper timeOffCreateHelper;
    private final TimeOffDataMapper timeOffDataMapper;

    public TimeOffCreateCommandHandler(CategoryRepository categoryRepository,
                                       TimeOffCreateHelper timeOffCreateHelper,
                                       TimeOffDataMapper timeOffDataMapper) {
        this.categoryRepository = categoryRepository;
        this.timeOffCreateHelper = timeOffCreateHelper;
        this.timeOffDataMapper = timeOffDataMapper;
    }

    public TimeOffRequest commandHandler(String categoryId, CreateTimeOffCommand command) {
        Optional<Category> category = categoryRepository.findById(new CategoryId(categoryId));
        if (!category.isPresent()) {
            throw new CategoryDomainException("Unrecognised category!");
        }
        TimeOffRequest timeOffRequest =
                timeOffDataMapper.createTimeOffCommandToTimeOffRequest(command);
        timeOffRequest.setCategoryId(category.get().getId());
        timeOffRequest.setCategoryCode(category.get().getCategoryCode());
        return timeOffCreateHelper.persistRequest(timeOffRequest);
    }
}
