package com.cercli.edms.application.controller;

import com.cercli.common.domain.dto.EntityResponse;
import com.cercli.edms.domain.application.service.dto.command.CreateEmployeeCommand;
import com.cercli.edms.domain.application.service.dto.command.UpdateEmployeeCommand;
import com.cercli.edms.domain.application.service.dto.response.QueryEmployeeResponse;
import com.cercli.edms.domain.application.service.port.input.EdmsApplicationService;
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
@RequestMapping(value = "/api/v1/employees")
public class EmployeeController {
    private final EdmsApplicationService edmsApplicationService;

    public EmployeeController(EdmsApplicationService edmsApplicationService) {
        this.edmsApplicationService = edmsApplicationService;
    }

    /**
     * Get all employees
     * @return
     */
    @GetMapping()
    public ResponseEntity<EntityResponse<QueryEmployeeResponse>> getAll() {
        log.info("Get employees request received");
        return ResponseEntity.ok(edmsApplicationService.getAll());
    }

    /**
     * Get all employees
     * @return
     */
    @GetMapping("/{employee_id}")
    public ResponseEntity<EntityResponse<QueryEmployeeResponse>> get(@PathVariable("employee_id") String employeeId) {
        log.info("Get employees request received");
        return ResponseEntity.ok(edmsApplicationService.get(employeeId));
    }

    /**
     * Update existing employee request
     * @param employeeId
     * @param command
     * @return
     */
    @PatchMapping("/{employee_id}")
    public ResponseEntity<EntityResponse> update(@PathVariable("employee_id") final String employeeId,
                                                        @RequestBody @Validated UpdateEmployeeCommand command) {
        log.info("Update employee request received: {}", command.toString());
        return ResponseEntity.ok(edmsApplicationService.update(employeeId, command));
    }

    /**
     * Create employee request
     * @param command
     * @return
     */
    @PostMapping()
    public ResponseEntity<EntityResponse> create(@RequestBody @Validated CreateEmployeeCommand command) {
        log.info("Create employee request received: {}", command.toString());
        return ResponseEntity.ok(edmsApplicationService.create(command));
    }
}
