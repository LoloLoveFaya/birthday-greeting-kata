package com.tdd.app.infrastructure;

import com.tdd.app.domain.EmailService;
import com.tdd.app.domain.Employee;
import com.tdd.app.domain.Mail;
import com.tdd.app.domain.MailBuilder;

public class FakeEmailService implements EmailService {
    public Mail sendMail(Employee employee) {
        return new MailBuilder()
                .setRecipient(employee.email)
                .setContent(String.format("Happy birthday, dear %s!", employee.firstName))
                .setSubject("Happy birthday!")
                .build();
    }
}
