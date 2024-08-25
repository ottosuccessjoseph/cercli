package com.cercli.notifications.domain.application.service.port.output;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
public interface NotificationService {
    void send(String recipient, String subject, String message);
}
