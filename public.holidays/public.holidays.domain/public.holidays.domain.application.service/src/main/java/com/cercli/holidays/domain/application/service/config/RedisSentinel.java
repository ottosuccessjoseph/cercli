package com.cercli.holidays.domain.application.service.config;

import lombok.Data;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Data
public class RedisSentinel {
    private String master;
    private String nodes;
    private String username;
    private String password;
}
