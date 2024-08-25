package com.cercli.time.off.domain.application.service.mapper;

import com.cercli.common.domain.DateHelper;
import com.cercli.common.domain.value.object.CategoryCode;
import com.cercli.common.domain.value.object.CategoryId;
import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.common.domain.value.object.TimeOffId;
import com.cercli.time.off.domain.application.service.dto.command.CategoryCommand;
import com.cercli.time.off.domain.application.service.dto.command.CreateTimeOffCommand;
import com.cercli.time.off.domain.core.entity.Category;
import com.cercli.time.off.domain.core.entity.TimeOffRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Component
public class CategoryDataMapper {

    public Category categoryCommandToCategory(CategoryCommand command) {
        return Category.builder()
                .id(new CategoryId(UUID.randomUUID().toString()))
                .name(command.getName())
                .categoryCode(CategoryCode.valueOf(command.getCode()))
                .build();
    }
}
