package com.cercli.time.off.data.access.entity;

import com.cercli.common.domain.value.object.CategoryCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.UUID;

/**
 * @author Success.otto
 * @since 0.0.1
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@Document(collection = "time-off-request")
public class TimeOffRequestEntity {

    @Id
    private UUID id;
    @Field("request_category_id")
    private String categoryId;
    @Field("category_code")
    private CategoryCode categoryCode;
    @Field("employee_id")
    private String employeeId;
    @Field("start_date")
    private Instant startDate;
    @Field("end_date")
    private Instant endDate;
}
