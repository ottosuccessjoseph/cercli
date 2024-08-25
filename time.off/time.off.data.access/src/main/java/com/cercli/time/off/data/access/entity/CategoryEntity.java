package com.cercli.time.off.data.access.entity;

import com.cercli.common.domain.value.object.CategoryCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Success.otto
 * @since 0.0.1
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@Document(collection = "category")
public class CategoryEntity {
    @Id
    private String id;
    private String name;
    @Field("code")
    private CategoryCode categoryCode;
}
