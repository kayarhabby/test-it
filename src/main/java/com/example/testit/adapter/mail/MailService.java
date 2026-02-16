package com.example.testit.adapter.mail;

import com.example.testit.model.User;

public interface MailService {

    /**
     * Envoie un mail à l'utilisateur avec sujet et message.
     * @param user le destinataire
     * @param subject le sujet du mail
     * @param message le contenu du message
     */
    void sendMail(User user, String subject, String message);

    /**
     * Envoie le mail de cloture du ticket (héritage).
     * @param user
     */
    void sendMailCloture(User user);

}
