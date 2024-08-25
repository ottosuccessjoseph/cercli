package com.cercli.time.off.domain.core.entity;

import com.cercli.common.domain.value.object.CategoryCode;
import com.cercli.common.domain.value.object.CategoryId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Category implements Serializable {
    private CategoryId id;
    private String name;
    private CategoryCode categoryCode;
    private boolean isEnabled;
}
