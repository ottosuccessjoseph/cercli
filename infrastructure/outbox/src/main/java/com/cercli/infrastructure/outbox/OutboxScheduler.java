package com.cercli.infrastructure.outbox;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface OutboxScheduler {
    void processOutboxMessage();
}
