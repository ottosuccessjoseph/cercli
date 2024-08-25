package com.cercli.holidays.messaging.producer;

import com.cercli.common.domain.event.DomainEvent;
import com.cercli.common.domain.event.EmployeeHolidayRequestedEvent;
import com.cercli.redis.common.producer.exception.RedisProducerException;
import com.cercli.redis.common.producer.service.RedisProducer;
import io.lettuce.core.RedisException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.Serializable;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class EmployeeHolidayRequestedEventProducer<String extends Serializable> implements RedisProducer<String, EmployeeHolidayRequestedEvent> {

    private final ReactiveRedisTemplate<String, DomainEvent> reactiveRedisTemplate;

    public EmployeeHolidayRequestedEventProducer(ReactiveRedisTemplate reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    @Override
    public Mono<RecordId> send(String key, EmployeeHolidayRequestedEvent value) {
        log.info("Sending message={} to stream={}", value, key);
        try {
            return reactiveRedisTemplate.opsForStream().add(StreamRecords.newRecord().ofObject(value).withStreamKey(key));
        } catch (RedisException e) {
            log.error("Error on redis producer with key: {}, message: {} and exception: {}", key, value,
                    e.getMessage());
            throw new RedisProducerException("Error on redis producer with key: " + key + " and message: " + value);
        }
    }
}
