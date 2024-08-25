package com.cercli.edms.domain.application.service;

import com.cercli.common.domain.dto.EntityResponse;
import com.cercli.common.domain.dto.ResponseStatus;
import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.edms.domain.application.service.dto.command.CreateEmployeeCommand;
import com.cercli.edms.domain.application.service.dto.command.UpdateEmployeeCommand;
import com.cercli.edms.domain.application.service.dto.response.CreateEmployeeResponse;
import com.cercli.edms.domain.application.service.dto.response.QueryEmployeeResponse;
import com.cercli.edms.domain.application.service.dto.response.UpdateEmployeeResponse;
import com.cercli.edms.domain.application.service.port.input.EdmsApplicationService;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Component
public class EdmsApplicationServiceImpl implements EdmsApplicationService {

    private final EmployeeQueryHandler employeeQueryHandler;
    private final EmployeeCreateCommandHandler employeeCreateCommandHandler;
    private final EmployeeUpdateCommandHandler employeeUpdateCommandHandler;

    public EdmsApplicationServiceImpl(EmployeeQueryHandler employeeQueryHandler,
                                      EmployeeCreateCommandHandler employeeCreateCommandHandler,
                                      EmployeeUpdateCommandHandler employeeUpdateCommandHandler) {
        this.employeeQueryHandler = employeeQueryHandler;
        this.employeeCreateCommandHandler = employeeCreateCommandHandler;
        this.employeeUpdateCommandHandler = employeeUpdateCommandHandler;
    }

    @Override
    public EntityResponse<QueryEmployeeResponse> get(String employeeId) {
        return EntityResponse.<QueryEmployeeResponse>builder()
                .status(ResponseStatus.SUCCESS)
                .timestamp(ZonedDateTime.now())
                .message("Operation was successful!")
                .data(QueryEmployeeResponse.queryEmployeeResponseBuilder()
                        .employee(employeeQueryHandler.getById(employeeId))
                        .build())
                .build();
    }

    @Override
    public EntityResponse<QueryEmployeeResponse> getAll() {
        return EntityResponse.<QueryEmployeeResponse>builder()
                .status(ResponseStatus.SUCCESS)
                .timestamp(ZonedDateTime.now())
                .message("Operation was successful!")
                .data(QueryEmployeeResponse.queryEmployeeResponseBuilder()
                        .employees(employeeQueryHandler.getAll())
                        .build())
                .build();
    }

    @Override
    public EntityResponse<CreateEmployeeResponse> create(CreateEmployeeCommand command) {
        return EntityResponse.<CreateEmployeeResponse>builder()
                .status(ResponseStatus.SUCCESS)
                .timestamp(ZonedDateTime.now())
                .message("Operation was successful!")
                .data(CreateEmployeeResponse.queryEmployeeResponseBuilder()
                        .id(employeeCreateCommandHandler.commandHandler(command).getId().getValue())
                        .build())
                .build();
    }

    @Override
    public EntityResponse<UpdateEmployeeResponse> update(String employeeId, UpdateEmployeeCommand command) {
        return EntityResponse.<UpdateEmployeeResponse>builder()
                .status(ResponseStatus.SUCCESS)
                .timestamp(ZonedDateTime.now())
                .message("Operation was successful!")
                .data(UpdateEmployeeResponse.queryEmployeeResponseBuilder()
                        .id(employeeUpdateCommandHandler.commandHandler(employeeId, command).getId().getValue())
                        .build())
                .build();
    }
}
