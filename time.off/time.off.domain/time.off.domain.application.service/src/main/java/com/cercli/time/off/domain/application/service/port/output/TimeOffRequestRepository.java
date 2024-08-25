package com.cercli.time.off.domain.application.service.port.output;

import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.time.off.domain.core.entity.TimeOffRequest;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface TimeOffRequestRepository {

    Optional<List<TimeOffRequest>> findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(EmployeeId employeeId,
                                                                                                       ZonedDateTime endDate, ZonedDateTime startDate);
    TimeOffRequest save(TimeOffRequest timeOffRequest);;
}
