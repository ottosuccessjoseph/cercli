package com.cercli.holidays.domain.application.service.config;

import lombok.Data;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Data
public class RedisStream {
    private int instance;
    private RedisStreamRequest request;
}
