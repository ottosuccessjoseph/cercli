package com.cercli.notifications.data.access.entity;

import com.cercli.common.domain.value.object.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

/**
 * @author Success.otto
 * @since 0.0.1
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@Document(collection = "employee")
public class EmployeeEntity {
    @Id
    private String id;
    private String name;
    private String position;
    private String email;
    private Money salary;
    private String address;
    @Field("created_at")
    private Instant createdAt;
    @Field("updated_at")
    private Instant updatedAt;
}
