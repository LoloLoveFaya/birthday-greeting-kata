package com.tdd.app.application;

import com.tdd.app.domain.EmailService;
import com.tdd.app.domain.Employee;
import com.tdd.app.domain.EmployeeRepository;
import com.tdd.app.domain.Mail;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BirthdayService {

    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    public BirthdayService(EmployeeRepository employeeRepository, EmailService emailService) {
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
    }

    public List<Mail> sendGreetings(LocalDate localDate) {
        List<Employee> birthdayEmployees = employeeRepository.findEmployeesBornOn(localDate.getMonthValue(),
                localDate.getDayOfMonth());

        return birthdayEmployees.stream().map(emailService::sendMail)
                .collect(Collectors.toList());
    }
}