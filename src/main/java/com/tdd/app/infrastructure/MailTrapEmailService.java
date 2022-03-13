package com.tdd.app.infrastructure;

import com.tdd.app.domain.EmailService;
import com.tdd.app.domain.Employee;
import com.tdd.app.domain.Mail;
import com.tdd.app.domain.MailBuilder;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailTrapEmailService implements EmailService {

    public Mail sendMail(Employee employee) {
        Mail mail = new MailBuilder()
                .setRecipient(employee.email)
                .setContent(String.format("Happy birthday, dear %s!", employee.firstName))
                .setSubject("Happy birthday!")
                .build();

        // Put recipient’s address
        String to = mail.recipient;

        // Put sender’s address
        String from = "from@example.com";
        final String username = "yours";//username generated by Mailtrap
        final String password = "yours";//password generated by Mailtrap

        // Paste host address from the SMTP settings tab in your Mailtrap Inbox
        String host = "smtp.mailtrap.io";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");//it’s optional in Mailtrap
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "2525");// use one of the options in the SMTP settings tab in your Mailtrap Inbox

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(from));

            // Set To: header field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(mail.subject);

            // Put the content of your message
            message.setText(mail.content);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return mail;
    }
}
