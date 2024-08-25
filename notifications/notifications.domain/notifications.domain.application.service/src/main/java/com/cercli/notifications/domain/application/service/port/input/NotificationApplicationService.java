package com.cercli.notifications.domain.application.service.port.input;

import com.cercli.common.domain.event.DomainEvent;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface NotificationApplicationService {

    public void eventHandler(DomainEvent event);
}
