package com.cercli.time.off.container;

import com.cercli.time.off.domain.application.service.config.RedisConfigData;
import com.cercli.time.off.domain.application.service.dto.command.CategoryCommand;
import com.cercli.time.off.domain.application.service.port.input.TimeOffApplicationService;
import org.springframework.boot.CommandLineRunner;
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
        "com.cercli.time.off.data.access.entity"
})
@EnableMongoRepositories(basePackages = {
        "com.cercli.time.off.data.access.repository"
})
@SpringBootApplication(scanBasePackages = "com.cercli")
@EnableConfigurationProperties(RedisConfigData.class)
public class TimeOffApplication implements CommandLineRunner {

    private final TimeOffApplicationService timeOffApplicationService;

    public TimeOffApplication(TimeOffApplicationService timeOffApplicationService) {
        this.timeOffApplicationService = timeOffApplicationService;
    }

    public static void main(String args[]) {
        SpringApplication.run(TimeOffApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        timeOffApplicationService.createCategory(CategoryCommand.builder()
                        .name("Annual Leave")
                        .code("AL")
                .build());
        timeOffApplicationService.createCategory(CategoryCommand.builder()
                .name("Sick Leave")
                .code("SL")
                .build());
        timeOffApplicationService.createCategory(CategoryCommand.builder()
                .name("Work Remotely")
                .code("WR")
                .build());
    }
}
