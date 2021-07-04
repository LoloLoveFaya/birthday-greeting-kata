package com.tdd.app.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {

    public String lastName;

    public String firstName;

    public LocalDate dateOfBirth;

    public String email;

    public Employee(String lastName, String firstName, LocalDate dateOfBirth, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(email, employee.email);
    }

    public boolean isBirthdayOn(LocalDate localDate) {
        return this.dateOfBirth.getMonth() == localDate.getMonth() &&
                this.dateOfBirth.getDayOfMonth() == localDate.getDayOfMonth();
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
