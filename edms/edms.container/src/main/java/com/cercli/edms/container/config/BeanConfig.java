package com.cercli.edms.container.config;

import com.cercli.edms.domain.core.service.EmployeeDomainService;
import com.cercli.edms.domain.core.service.EmployeeDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Configuration
public class BeanConfig {

    @Bean
    public EmployeeDomainService employeeDomainService() {
        return new EmployeeDomainServiceImpl();
    }
}
