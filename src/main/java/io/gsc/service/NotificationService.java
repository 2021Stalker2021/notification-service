package io.gsc.service;

import io.gsc.model.constants.ApiConstants;
import io.gsc.model.event.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailService emailService;

    public void processUserEvent(UserEvent event) {
        String email = event.getEmail();

        if (event.getActionType() == UserEvent.ActionType.CREATE) {
            emailService.sendNotification(email,
                    ApiConstants.SUBJECT_WELCOME,
                    ApiConstants.MSG_WELCOME);

        } else {
            emailService.sendNotification(email,
                    ApiConstants.SUBJECT_DELETE,
                    ApiConstants.MSG_DELETE);
        }
    }
}
