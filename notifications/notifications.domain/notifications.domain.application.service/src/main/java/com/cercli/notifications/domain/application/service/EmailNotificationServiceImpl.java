package com.cercli.notifications.domain.application.service;

import com.cercli.notifications.domain.application.service.port.output.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author Success.Otto
 * @since 0.0.1
 */
@Slf4j
@Component
public class EmailNotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;

    public EmailNotificationServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(String recipient, String subject, String message) {
        log.info("Sending mail to recipient={}, of subject={} and message={}", recipient, subject, message);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);
        log.info("Mail successfully sent!");
    }
}
