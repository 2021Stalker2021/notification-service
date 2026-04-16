package io.gsc.consumer;

import io.gsc.model.event.UserEvent;
import io.gsc.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserEventConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void consume(UserEvent event) {
        log.info("Received event from Kafka: {}", event);

        String email = event.getEmail();
        if (event.getActionType() == UserEvent.ActionType.CREATE) {
            emailService.sendNotification(email, "Добро пожаловать!",
                    "Здравствуйте! Ваш аккаунт на сайте успешно создан.");
        } else {
            emailService.sendNotification(email, "Аккаунт удален",
                    "Здравствуйте! Ваш аккаунт был удалён.");
        }
    }
}
