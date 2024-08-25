package com.cercli.time.off.domain.application.service.mapper;

import com.cercli.common.domain.DateHelper;
import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.common.domain.value.object.TimeOffId;
import com.cercli.time.off.domain.application.service.dto.command.CreateTimeOffCommand;
import com.cercli.time.off.domain.core.entity.TimeOffRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Component
public class TimeOffDataMapper {

    public TimeOffRequest createTimeOffCommandToTimeOffRequest(CreateTimeOffCommand command) {
        return TimeOffRequest.builder()
                .id(new TimeOffId(UUID.randomUUID()))
                .employeeId(new EmployeeId(command.getEmployeeId()))
                .startDate(DateHelper.convert(command.getStartDate()))
                .endDate(DateHelper.convert(command.getEndDate()))
                .build();
    }
}
