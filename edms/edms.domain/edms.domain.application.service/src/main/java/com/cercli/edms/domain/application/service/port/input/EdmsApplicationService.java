package com.cercli.edms.domain.application.service.port.input;

import com.cercli.common.domain.dto.EntityResponse;
import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.edms.domain.application.service.dto.command.CreateEmployeeCommand;
import com.cercli.edms.domain.application.service.dto.command.UpdateEmployeeCommand;
import com.cercli.edms.domain.application.service.dto.response.CreateEmployeeResponse;
import com.cercli.edms.domain.application.service.dto.response.QueryEmployeeResponse;
import com.cercli.edms.domain.application.service.dto.response.UpdateEmployeeResponse;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface EdmsApplicationService {
    EntityResponse<QueryEmployeeResponse> get(String employeeId);
    EntityResponse<QueryEmployeeResponse> getAll();
    EntityResponse<CreateEmployeeResponse> create(CreateEmployeeCommand command);
    EntityResponse<UpdateEmployeeResponse> update(String employeeId, UpdateEmployeeCommand command);
}
