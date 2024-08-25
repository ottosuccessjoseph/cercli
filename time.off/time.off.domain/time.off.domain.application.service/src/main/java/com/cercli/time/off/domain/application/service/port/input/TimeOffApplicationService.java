package com.cercli.time.off.domain.application.service.port.input;

import com.cercli.common.domain.dto.EntityResponse;
import com.cercli.time.off.domain.application.service.dto.command.CategoryCommand;
import com.cercli.time.off.domain.application.service.dto.command.CreateTimeOffCommand;
import com.cercli.time.off.domain.application.service.dto.response.CreateTimeOffResponse;
import com.cercli.time.off.domain.application.service.dto.response.QueryCategoryResponse;
import com.cercli.time.off.domain.core.entity.Category;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface TimeOffApplicationService {
    EntityResponse<QueryCategoryResponse> getAll();
    EntityResponse<CreateTimeOffResponse> createTimeOff(String categoryId, CreateTimeOffCommand command);
    Category createCategory(CategoryCommand command);
}
