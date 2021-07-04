package com.tdd.app;

import com.tdd.app.application.BirthdayService;
import com.tdd.app.domain.EmailService;
import com.tdd.app.domain.Employee;
import com.tdd.app.domain.EmployeeRepository;
import com.tdd.app.domain.Mail;
import com.tdd.app.infrastructure.EmployeeDataSourceRepository;
import com.tdd.app.infrastructure.FakeEmailService;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;

public class BirthdayServiceTest {

    @Test
    public void shouldNotSendGreetingsWithNoEmployees() {
        EmployeeRepository employeeRepository = new EmployeeDataSourceRepository(List::of);
        EmailService emailService = new FakeEmailService();
        BirthdayService birthdayService = new BirthdayService(employeeRepository, emailService);

        List<Mail> sentMails = birthdayService.sendGreetings(LocalDate.now());

        assertTrue(sentMails.isEmpty());
    }

    @Test
    public void shouldNotSendGreetingsWhenNoOneBirthday(){
        EmployeeRepository employeeRepository = new EmployeeDataSourceRepository(() -> List.of(
                new Employee("Doe", "John", LocalDate.of(1982, 10, 8), "john.doe@foobar.com"),
                new Employee("Ann", "Mary", LocalDate.of(1975, 9, 11), "mary.ann@foobar.com")
        ));
        EmailService emailService = new FakeEmailService();
        BirthdayService birthdayService = new BirthdayService(employeeRepository, emailService);

        List<Mail> sentMails = birthdayService.sendGreetings(LocalDate.of(2021, 1, 1));

        assertTrue(sentMails.isEmpty());
    }

    @Test
    public void shouldSendOneGreeting(){
        EmployeeRepository employeeRepository = new EmployeeDataSourceRepository(() -> List.of(
                new Employee("Doe", "John", LocalDate.of(1982, 10, 8), "john.doe@foobar.com"),
                new Employee("Ann", "Mary", LocalDate.of(1975, 5, 11), "mary.ann@foobar.com")
        ));
        EmailService emailService = new FakeEmailService();
        BirthdayService birthdayService = new BirthdayService(employeeRepository, emailService);

        List<Mail> sentMails = birthdayService.sendGreetings(LocalDate.of(2021, 5, 11));

        assertEquals(1, sentMails.size());
        assertEquals("mary.ann@foobar.com", sentMails.get(0).recipient);
        assertEquals("Happy birthday, dear Mary!", sentMails.get(0).content);
        assertEquals("Happy birthday!", sentMails.get(0).subject);
    }

    @Test
    public void shouldSendManyGreetings(){
        EmployeeRepository employeeRepository = new EmployeeDataSourceRepository(() -> List.of(
                new Employee("Doe", "John", LocalDate.of(1982, 9, 11), "john.doe@foobar.com"),
                new Employee("Ann", "Mary", LocalDate.of(1975, 9, 11), "mary.ann@foobar.com")
        ));
        EmailService emailService = new FakeEmailService();
        BirthdayService birthdayService = new BirthdayService(employeeRepository, emailService);

        List<Mail> sentMails = birthdayService.sendGreetings(LocalDate.of(2021, 9, 11));

        assertEquals(2, sentMails.size());

        assertEquals("john.doe@foobar.com", sentMails.get(0).recipient);
        assertEquals("Happy birthday, dear John!", sentMails.get(0).content);
        assertEquals("Happy birthday!", sentMails.get(0).subject);

        assertEquals("mary.ann@foobar.com", sentMails.get(1).recipient);
        assertEquals("Happy birthday, dear Mary!", sentMails.get(1).content);
        assertEquals("Happy birthday!", sentMails.get(1).subject);
    }
}
