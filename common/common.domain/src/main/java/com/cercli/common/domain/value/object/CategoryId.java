package com.cercli.common.domain.value.object;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public class CategoryId extends BaseId<String> implements Serializable {
    public CategoryId(String value) {
        super(value);
    }
}
