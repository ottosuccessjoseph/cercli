package com.cercli.redis.common.producer.exception;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public class RedisProducerException extends RuntimeException {
    public RedisProducerException(String message) {
        super(message);
    }
}

