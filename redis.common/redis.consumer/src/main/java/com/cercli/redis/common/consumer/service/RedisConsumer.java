package com.cercli.redis.common.consumer.service;

import com.cercli.common.domain.event.DomainEvent;
import org.springframework.data.redis.connection.stream.ObjectRecord;

import java.io.Serializable;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface RedisConsumer<K extends Serializable, E extends DomainEvent<?>> {
    /**
     * Consumes messages from the stream
     * @param objectRecord
     */
    void receive(ObjectRecord<K, E> objectRecord);
}
