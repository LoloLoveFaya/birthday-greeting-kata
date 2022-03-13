package com.tdd.app;

import com.tdd.app.domain.Employee;
import com.tdd.app.domain.Mail;
import com.tdd.app.infrastructure.FakeEmailService;
import com.tdd.app.infrastructure.MailTrapEmailService;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class MailTrapServiceTest {

    @Test
    public void shouldSendAnEmail() {
        MailTrapEmailService mailTrapEmailService = new MailTrapEmailService();
        Employee employee = new Employee("Robert", "Zane",
                LocalDate.of(1994, 8, 16), "essognim.faya@mail.com");

        Mail mail = mailTrapEmailService.sendMail(employee);

        assertEquals("essognim.faya@mail.com", mail.recipient);
        assertEquals("Happy birthday, dear Zane!", mail.content);
        assertEquals("Happy birthday!", mail.subject);
    }
}
