package io.gsc.controller;

import io.gsc.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendManualNotification(
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String message) {

        emailService.sendNotification(email, subject, message);
        return ResponseEntity.ok("Письмо успешно отправлено на " + email);
    }
}
