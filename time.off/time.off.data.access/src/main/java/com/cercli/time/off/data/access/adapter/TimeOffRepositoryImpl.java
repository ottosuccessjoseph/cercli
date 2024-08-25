package com.cercli.time.off.data.access.adapter;

import com.cercli.common.domain.value.object.EmployeeId;
import com.cercli.time.off.data.access.mapper.TimeOffRequestDataAccessMapper;
import com.cercli.time.off.data.access.repository.TimeOffRequestMongoRepository;
import com.cercli.time.off.domain.application.service.port.output.TimeOffRequestRepository;
import com.cercli.time.off.domain.core.entity.TimeOffRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class TimeOffRepositoryImpl implements TimeOffRequestRepository {

    private final TimeOffRequestMongoRepository timeOffRequestMongoRepository;
    private final TimeOffRequestDataAccessMapper timeOffRequestDataAccessMapper;

    public TimeOffRepositoryImpl(TimeOffRequestMongoRepository timeOffRequestMongoRepository,
                                 TimeOffRequestDataAccessMapper timeOffRequestDataAccessMapper) {
        this.timeOffRequestMongoRepository = timeOffRequestMongoRepository;
        this.timeOffRequestDataAccessMapper = timeOffRequestDataAccessMapper;
    }

    @Override
    public Optional<List<TimeOffRequest>> findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(EmployeeId employeeId, ZonedDateTime endDate, ZonedDateTime startDate) {
        return Optional.of(timeOffRequestMongoRepository.findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(employeeId.getValue(), endDate.toInstant(), startDate.toInstant())
                .get()
                .stream()
                .map(timeOffRequestDataAccessMapper::timeOffRequestEntityToTimeOffRequest)
                .collect(Collectors.toList()));
    }

    @Override
    public TimeOffRequest save(TimeOffRequest timeOffRequest) {
        return timeOffRequestDataAccessMapper.timeOffRequestEntityToTimeOffRequest(timeOffRequestMongoRepository
                .save(timeOffRequestDataAccessMapper.timeOffRequestToTimeOffRequestEntity(timeOffRequest)));
    }
}
