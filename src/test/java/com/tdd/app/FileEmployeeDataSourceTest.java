package com.tdd.app;

import com.tdd.app.domain.Employee;
import com.tdd.app.infrastructure.FileEmployeeDataSource;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class FileEmployeeDataSourceTest {

    @Test
    public void shouldReadEmployeesFromFile() {
        FileEmployeeDataSource fileEmployeeDataSource = new FileEmployeeDataSource("src/test/java/com/tdd/app/employees.csv");

        List<Employee> employees = fileEmployeeDataSource.read();

        assertEquals(2, employees.size());
        assertEquals(new Employee("Doe", "John", LocalDate.of(1982, 10, 8), "john.doe@foobar.com"), employees.get(0));
        assertEquals(new Employee("Ann", "Mary", LocalDate.of(1975, 9, 11), "mary.ann@foobar.com"), employees.get(1));
    }

    @Test
    public void shouldReturnEmptyListIfFileNotFound() {
        FileEmployeeDataSource fileEmployeeDataSource = new FileEmployeeDataSource("inexistantFile.csv");

        List<Employee> employees = fileEmployeeDataSource.read();

        assertTrue(employees.isEmpty());
    }
}