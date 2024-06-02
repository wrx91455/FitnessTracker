package com.capgemini.wsb.fitnesstracker.service;

import com.capgemini.wsb.fitnesstracker.email.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendEmail() {
        String to = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        emailService.sendEmail(to, subject, body);

        // Verify that the email was sent (implementation depends on your EmailService logic)
        verify(emailService).sendEmail(to, subject, body);
    }
}
