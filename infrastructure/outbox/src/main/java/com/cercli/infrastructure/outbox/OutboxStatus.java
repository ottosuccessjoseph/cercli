package com.cercli.infrastructure.outbox;

import java.io.Serializable;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public enum OutboxStatus implements Serializable {
    STARTED, COMPLETED, FAILED, PENDING
}
