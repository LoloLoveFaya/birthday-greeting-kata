package com.tdd.app.infrastructure;

import com.tdd.app.domain.EmailService;
import com.tdd.app.domain.Employee;
import com.tdd.app.domain.Mail;

public class FakeEmailService implements EmailService {
    public Mail sendMail(Employee employee) {
        return new Mail(employee.email, String.format("Happy birthday, dear %s!", employee.firstName), "Happy birthday!");
    }
}
