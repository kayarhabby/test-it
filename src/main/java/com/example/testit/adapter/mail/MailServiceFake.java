package com.example.testit.adapter.mail;

import com.example.testit.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service

public class MailServiceFake implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceFake.class);

    @Override
    public void sendMail(User user, String subject, String message) {

        logger.info("Fake Mail to " + user.getUsername() + ": " + subject + " - " + message);
    }

    @Override
    public void sendMailCloture(User user) {
        sendMail(user, "Tâche terminée", "Votre tâche a été terminée.");
    }
}
