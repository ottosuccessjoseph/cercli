package com.cercli.notifications.container.config;

import com.cercli.common.domain.event.EmployeeHolidayRequestedEvent;
import com.cercli.notifications.domain.application.service.config.RedisConfigData;
import com.cercli.notifications.messaging.consumer.EmployeeHolidayRequestedEventConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Component
public class Subscriptions {

    private final RedisConfigData redisConfigData;
    private final EmployeeHolidayRequestedEventConsumer employeeHolidayRequestedEventConsumer;

    public Subscriptions(RedisConfigData redisConfigData,
                         EmployeeHolidayRequestedEventConsumer employeeHolidayRequestedEventConsumer) {
        this.redisConfigData = redisConfigData;
        this.employeeHolidayRequestedEventConsumer = employeeHolidayRequestedEventConsumer;
    }

    @Bean
    public Subscription userCreatedEventSubscription(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        var options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofSeconds(1))
                .targetType(EmployeeHolidayRequestedEvent.class)
                .build();
        var listenerContainer = StreamMessageListenerContainer
                .create(redisConnectionFactory, options);

        var subscription = listenerContainer.receiveAutoAck(
                Consumer.from(redisConfigData.getStreams().getRequest().getEmployeeHolidayRequested(),
                        InetAddress.getLocalHost().getHostName() + redisConfigData.getStreams().getInstance()),
                StreamOffset.create(redisConfigData.getStreams().getRequest().getEmployeeHolidayRequested(), ReadOffset.lastConsumed()),
                (record) -> {
                    employeeHolidayRequestedEventConsumer.receive(record);
                    redisConnectionFactory.getConnection().xDel(record.getStream().split(":")[0].getBytes(StandardCharsets.UTF_8), record.getId().getValue());
                });

        try (RedisConnection connection = redisConnectionFactory.getConnection()) {
            if (!connection.streamCommands().xInfoGroups(redisConfigData.getStreams().getRequest().getEmployeeHolidayRequested().getBytes(StandardCharsets.UTF_8)).stream()
                    .anyMatch(info -> info.groupName().equals(redisConfigData.getStreams().getRequest().getEmployeeHolidayRequested()))) {
                // Create the consumer group if it does not exist
                connection.streamCommands()
                        .xGroupCreate(redisConfigData.getStreams().getRequest().getEmployeeHolidayRequested().getBytes(), redisConfigData.getStreams().getRequest().getEmployeeHolidayRequested(), ReadOffset.from("$"), true);
            }
        }
        listenerContainer.start();
        return subscription;
    }
}
