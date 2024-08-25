package com.cercli.holidays.domain.application.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisConfigData {
    private String host;
    private Integer port;
    private String timeOut;
    private String database;
    private Integer cacheTtl;
    private RedisSentinel sentinel;
    private RedisStream streams;

}
