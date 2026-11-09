package io.gsc.consumer;

import io.gsc.model.event.UserEvent;
import io.gsc.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserEventConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "notification-group")
    public void consume(UserEvent event) {
        log.info("Received event from Kafka: {}", event);

        notificationService.processUserEvent(event);
    }
}
