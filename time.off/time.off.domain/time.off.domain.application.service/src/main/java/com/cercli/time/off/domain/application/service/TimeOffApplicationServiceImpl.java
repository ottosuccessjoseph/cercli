package com.cercli.time.off.domain.application.service;

import com.cercli.common.domain.dto.EntityResponse;
import com.cercli.common.domain.dto.ResponseStatus;
import com.cercli.time.off.domain.application.service.dto.command.CategoryCommand;
import com.cercli.time.off.domain.application.service.dto.command.CreateTimeOffCommand;
import com.cercli.time.off.domain.application.service.dto.response.CategoryDto;
import com.cercli.time.off.domain.application.service.dto.response.CreateTimeOffResponse;
import com.cercli.time.off.domain.application.service.dto.response.QueryCategoryResponse;
import com.cercli.time.off.domain.application.service.port.input.TimeOffApplicationService;
import com.cercli.time.off.domain.core.entity.Category;
import com.cercli.time.off.domain.core.entity.TimeOffRequest;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Component
public class TimeOffApplicationServiceImpl implements TimeOffApplicationService {

    private final CategoryQueryHandler categoryQueryHandler;
    private final TimeOffCreateCommandHandler timeOffCreateCommandHandler;
    private final CategoryCreateCommandHandler categoryCreateCommandHandler;

    public TimeOffApplicationServiceImpl(CategoryQueryHandler categoryQueryHandler,
                                         TimeOffCreateCommandHandler timeOffCreateCommandHandler,
                                         CategoryCreateCommandHandler categoryCreateCommandHandler) {
        this.categoryQueryHandler = categoryQueryHandler;
        this.timeOffCreateCommandHandler = timeOffCreateCommandHandler;
        this.categoryCreateCommandHandler = categoryCreateCommandHandler;
    }

    @Override
    public EntityResponse<QueryCategoryResponse> getAll() {
        return EntityResponse.<QueryCategoryResponse>builder()
                .status(ResponseStatus.SUCCESS)
                .timestamp(ZonedDateTime.now())
                .message("Operation was successful!")
                .data(QueryCategoryResponse.queryCategoryResponseBuilder()
                        .categories(categoryQueryHandler.getAll()
                                .stream()
                                .map(category -> CategoryDto.builder()
                                        .id(category.getId().getValue())
                                        .name(category.getName()).build())
                                .collect(Collectors.toList()))
                        .build())
                .build();
    }

    @Override
    public EntityResponse<CreateTimeOffResponse> createTimeOff(String categoryId, CreateTimeOffCommand command) {
        TimeOffRequest timeOffRequest = timeOffCreateCommandHandler.commandHandler(categoryId, command);
        return EntityResponse.<CreateTimeOffResponse>builder()
                .status(ResponseStatus.SUCCESS)
                .timestamp(ZonedDateTime.now())
                .message("Operation was successful!")
                .data(CreateTimeOffResponse.createTimeOffResponseBuilder()
                        .id(timeOffRequest.getId().getValue().toString())
                        .build())
                .build();
    }

    @Override
    public Category createCategory(CategoryCommand command) {
        return categoryCreateCommandHandler.commandHandler(command);
    }
}
