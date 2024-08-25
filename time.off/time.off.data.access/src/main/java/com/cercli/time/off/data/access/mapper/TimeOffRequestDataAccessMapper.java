package com.cercli.time.off.data.access.mapper;

import com.cercli.common.domain.DateHelper;
import com.cercli.common.domain.value.object.CategoryId;
import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.common.domain.value.object.TimeOffId;
import com.cercli.time.off.data.access.entity.TimeOffRequestEntity;
import com.cercli.time.off.domain.core.entity.TimeOffRequest;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.UUID;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Component
public class TimeOffRequestDataAccessMapper {

    public TimeOffRequestEntity timeOffRequestToTimeOffRequestEntity(TimeOffRequest timeOffRequest) {
        return TimeOffRequestEntity.builder()
                .id(timeOffRequest.getId().getValue())
                .categoryId(timeOffRequest.getCategoryId().getValue())
                .categoryCode(timeOffRequest.getCategoryCode())
                .employeeId(timeOffRequest.getEmployeeId().getValue())
                .startDate(timeOffRequest.getStartDate().toInstant())
                .endDate(timeOffRequest.getEndDate().toInstant())
                .build();
    }

    public TimeOffRequest timeOffRequestEntityToTimeOffRequest(TimeOffRequestEntity timeOffRequestEntity) {
        return TimeOffRequest.builder()
                .id(new TimeOffId(timeOffRequestEntity.getId()))
                .categoryId(new CategoryId(timeOffRequestEntity.getCategoryId()))
                .categoryCode(timeOffRequestEntity.getCategoryCode())
                .employeeId(new EmployeeId(timeOffRequestEntity.getEmployeeId()))
                .startDate(timeOffRequestEntity.getStartDate().atZone(ZoneId.of(DateHelper.TIME_ZONE)))
                .endDate(timeOffRequestEntity.getEndDate().atZone(ZoneId.of(DateHelper.TIME_ZONE)))
                .build();
    }
}
