package io.gsc.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.gsc.model.constants.ApiConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @CircuitBreaker(name = "emailService", fallbackMethod = "fallbackSendNotification")
    public void sendNotification(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(ApiConstants.EMAIL_FROM);

        mailSender.send(message);
        log.info("Email sent to {}: {}", email, subject);
    }

    public void fallbackSendNotification(String email, String subject, String text, Throwable t) {
        log.error("Circuit Breaker сработал!");
        log.error("Не удалось отправить письмо на {}. Причина: {}", email, t.getMessage());
        log.warn("Данные уведомления сохранены в лог (заглушка): Тема: {}, Текст: {}", subject, text);
    }
}
