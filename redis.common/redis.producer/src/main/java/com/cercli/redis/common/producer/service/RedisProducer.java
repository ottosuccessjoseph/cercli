package com.cercli.redis.common.producer.service;

import com.cercli.common.domain.event.DomainEvent;
import org.springframework.data.redis.connection.stream.RecordId;
import reactor.core.publisher.Mono;

import java.io.Serializable;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface RedisProducer<K extends Serializable, V extends DomainEvent> {
    /**
     * Publishes message to the stream
     * @param key
     * @param value
     * @return
     */
    Mono<RecordId> send(K key, V value);
}
