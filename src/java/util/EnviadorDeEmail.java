/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author romulo
 */
public class EnviadorDeEmail {

    public static void enviarEmail(String assunto, String mensagem, String email, String senha) {

        final String emailDeEnvio = "sistemaSuperUeg@gmail.com";
        final String senhaEmail = "sistema@Super123";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailDeEnvio, senhaEmail);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailDeEnvio));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject(assunto);
            message.setText(mensagem);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
