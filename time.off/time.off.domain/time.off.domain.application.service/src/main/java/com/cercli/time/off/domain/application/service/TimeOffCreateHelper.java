package com.cercli.time.off.domain.application.service;

import com.cercli.common.domain.exception.TimeOffRequestDomainException;
import com.cercli.common.domain.value.object.CategoryCode;
import com.cercli.time.off.domain.application.service.port.output.TimeOffRequestRepository;
import com.cercli.time.off.domain.core.entity.TimeOffRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class TimeOffCreateHelper {
    private final TimeOffRequestRepository timeOffRequestRepository;

    public TimeOffCreateHelper(TimeOffRequestRepository timeOffRequestRepository) {
        this.timeOffRequestRepository = timeOffRequestRepository;
    }

    public TimeOffRequest persistRequest(TimeOffRequest timeOffRequest) {
        Optional<List<TimeOffRequest>> timeOffRequestList =
                timeOffRequestRepository.findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(timeOffRequest.getEmployeeId(),
                        timeOffRequest.getEndDate(), timeOffRequest.getStartDate());

        if (timeOffRequestList.isPresent() && timeOffRequestList.get().size() > 0) {
            boolean canRequestTimeOff = canRequestTimeOff(timeOffRequest, timeOffRequestList.get());
            if (!canRequestTimeOff) {
                throw new TimeOffRequestDomainException("Already requested for time-off!");
            }
        }
        return save(timeOffRequest);
    }

    private boolean canRequestTimeOff(TimeOffRequest timeOffRequest, List<TimeOffRequest> timeOffRequests) {
        for (TimeOffRequest request: timeOffRequests) {
            if (!CategoryCode.WR.equals(request.getCategoryCode()) && CategoryCode.WR.equals(timeOffRequest.getCategoryCode()))
                continue;
            if (!request.getCategoryCode().equals(timeOffRequest.getCategoryCode()) && CategoryCode.WR.equals(request.getCategoryCode()))
                return true;
            if (!request.getCategoryCode().equals(timeOffRequest.getCategoryCode())
                    || request.getCategoryCode().equals(timeOffRequest.getCategoryCode()))
                return false;
        }
        return true;
    }

    private TimeOffRequest save(TimeOffRequest timeOffRequest) {
        TimeOffRequest timeOffRequestResult = timeOffRequestRepository.save(timeOffRequest);
        if (null == timeOffRequestResult) {
            throw new TimeOffRequestDomainException("Error occurred trying to persist request!");
        }
        return timeOffRequestResult;
    }
}
