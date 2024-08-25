package com.cercli.notifications.messaging.consumer;

import com.cercli.common.domain.event.EmployeeHolidayRequestedEvent;
import com.cercli.notifications.domain.application.service.port.input.NotificationApplicationService;
import com.cercli.redis.common.consumer.service.RedisConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Service
public class EmployeeHolidayRequestedEventConsumer<K extends Serializable> implements RedisConsumer<K, EmployeeHolidayRequestedEvent> {

    private final NotificationApplicationService notificationApplicationService;

    public EmployeeHolidayRequestedEventConsumer(NotificationApplicationService notificationApplicationService) {
        this.notificationApplicationService = notificationApplicationService;
    }

    @Override
    public void receive(ObjectRecord<K, EmployeeHolidayRequestedEvent> objectRecord) {
        log.info("Received message={} from stream={}", objectRecord.getValue(), objectRecord.getStream());
        EmployeeHolidayRequestedEvent event = objectRecord.getValue();
        notificationApplicationService.eventHandler(event);
    }
}
