package com.cercli.notifications.container;

import com.cercli.notifications.domain.application.service.config.RedisConfigData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@EntityScan(basePackages = {
        "com.cercli.notifications.data.access.entity"
})
@EnableMongoRepositories(basePackages = {
        "com.cercli.notifications.data.access.repository"
})
@SpringBootApplication(scanBasePackages = "com.cercli.notifications")
@EnableConfigurationProperties(RedisConfigData.class)
@EnableAsync
public class NotificationsApplication {

    public static void main(String args[]) {
        SpringApplication.run(NotificationsApplication.class, args);
    }


}
