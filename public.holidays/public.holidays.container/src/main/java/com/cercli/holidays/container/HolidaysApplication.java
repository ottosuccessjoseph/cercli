package com.cercli.holidays.container;

import com.cercli.holidays.domain.application.service.config.RedisConfigData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
//@EntityScan(basePackages = {
//        "com.cercli.holidays.data.access.entity"
//})
//@EnableMongoRepositories(basePackages = {
//        "com.cercli.holidays.data.access.repository"
//})
@SpringBootApplication(scanBasePackages = "com.cercli.holidays")
@EnableConfigurationProperties(RedisConfigData.class)
public class HolidaysApplication {

    public static void main(String args[]) {
        SpringApplication.run(HolidaysApplication.class, args);
    }
}
