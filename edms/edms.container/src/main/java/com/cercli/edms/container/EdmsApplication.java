package com.cercli.edms.container;

import com.cercli.edms.domain.application.service.config.RedisConfigData;
import com.cercli.edms.domain.application.service.dto.command.CreateEmployeeCommand;
import com.cercli.edms.domain.application.service.port.input.EdmsApplicationService;
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
        "com.cercli.edms.data.access.entity"
})
@EnableMongoRepositories(basePackages = {
        "com.cercli.edms.data.access.repository"
})
@SpringBootApplication(scanBasePackages = "com.cercli")
@EnableConfigurationProperties(RedisConfigData.class)
@EnableAsync
public class EdmsApplication implements CommandLineRunner {

    private final EdmsApplicationService edmsApplicationService;

    public EdmsApplication(EdmsApplicationService edmsApplicationService) {
        this.edmsApplicationService = edmsApplicationService;
    }

    public static void main(String args[]) {
        SpringApplication.run(EdmsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        edmsApplicationService.create(CreateEmployeeCommand.builder()
                        .name("Success Otto")
                        .address("Dubai")
                        .email("ottosuccessjoseph@gmail.com")
                        .position("AE")
                        .salary("100000")
                .build());
    }
}
