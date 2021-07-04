package com.tdd.app;

import com.tdd.app.domain.Employee;
import com.tdd.app.domain.Mail;
import com.tdd.app.infrastructure.FakeEmailService;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;

public class FakeEmailServiceTest {

    @Test
    public void shouldSendAnEmail() {
        FakeEmailService emailService = new FakeEmailService();
        Employee employee = new Employee("Robert", "Zane", LocalDate.of(1994, 8, 16), "robzane@mail.fr");

        Mail mail = emailService.sendMail(employee);

        assertEquals("robzane@mail.fr", mail.recipient);
        assertEquals("Happy birthday, dear Zane!", mail.content);
        assertEquals("Happy birthday!", mail.subject);
    }
}
