package com.cercli.common.domain.value.object;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public class TimeOffId extends BaseId<UUID> implements Serializable {
    public TimeOffId(UUID value) {
        super(value);
    }
}
