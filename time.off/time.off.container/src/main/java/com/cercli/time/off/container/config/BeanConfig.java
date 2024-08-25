package com.cercli.time.off.container.config;

import com.cercli.time.off.domain.core.service.TimeOffDomainService;
import com.cercli.time.off.domain.core.service.serviceImpl.TimeOffDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Configuration
public class BeanConfig {

    @Bean
    public TimeOffDomainService timeOffRequestDomainService() {
        return new TimeOffDomainServiceImpl();
    }
}
