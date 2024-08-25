package com.cercli.time.off.application.controller;

import com.cercli.common.domain.dto.EntityResponse;
import com.cercli.time.off.domain.application.service.dto.command.CreateTimeOffCommand;
import com.cercli.time.off.domain.application.service.dto.response.QueryCategoryResponse;
import com.cercli.time.off.domain.application.service.port.input.TimeOffApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryController {

    private final TimeOffApplicationService timeOffApplicationService;

    public CategoryController(TimeOffApplicationService timeOffApplicationService) {
        this.timeOffApplicationService = timeOffApplicationService;
    }

    /**
     * Get all available categories
     * @return
     */
    @GetMapping()
    public ResponseEntity<EntityResponse<QueryCategoryResponse>> getAll() {
        log.info("Get categories request received");
        return ResponseEntity.ok(timeOffApplicationService.getAll());
    }

    /**
     * Create a new Time off request
     * @param categoryId
     * @param command
     * @return
     */
    @PostMapping("/{categoryId}/requests")
    public ResponseEntity<EntityResponse> createTimeOff(@PathVariable("categoryId") final String categoryId,
                                                   @RequestBody @Validated CreateTimeOffCommand command) {
        log.info("Create time-off request received: {}", command.toString());
        return ResponseEntity.ok(timeOffApplicationService.createTimeOff(categoryId, command));
    }
}
