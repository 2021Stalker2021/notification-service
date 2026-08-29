package io.gsc;

import io.gsc.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @MockitoBean
    private JavaMailSender mailSender;

    @Test
    void sendNotification_ShouldCallMailSender() {
        String email = "edward@gmail.com";
        String subject = "Test subject";
        String text = "Some text";

        emailService.sendNotification(email, subject, text);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
