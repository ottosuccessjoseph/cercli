package com.cercli.time.off.domain.core.entity;

import com.cercli.common.domain.value.object.CategoryCode;
import com.cercli.common.domain.value.object.CategoryId;
import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.common.domain.value.object.TimeOffId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class TimeOffRequest {
    private TimeOffId id;
    private CategoryId categoryId;
    private CategoryCode categoryCode;
    private EmployeeId employeeId;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;

}
