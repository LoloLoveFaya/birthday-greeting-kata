package com.tdd.app;

import com.tdd.app.domain.Employee;
import com.tdd.app.domain.EmployeeRepository;
import com.tdd.app.infrastructure.EmployeeDataSourceRepository;
import com.tdd.app.infrastructure.FileEmployeeDataSource;
import org.junit.Test;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeDataSourceRepositoryTest {

    @Test
    public void shouldReadNoEmployees() {
        EmployeeRepository employeeRepository = new EmployeeDataSourceRepository(ArrayList::new);

        List<Employee> employeesList = employeeRepository.getEmployees();

        assertTrue(employeesList.isEmpty());
    }

    @Test
    public void shouldReadOneEmployee() {
        EmployeeRepository employeeRepository = new EmployeeDataSourceRepository(() -> Arrays.asList(
                new Employee("Robert", "Zane", LocalDate.of(1994, 8, 16),
                        "robzane@mail.fr")));

        List<Employee> employeesList = employeeRepository.getEmployees();

        assertFalse(employeesList.isEmpty());
        assertEquals(new Employee("Robert", "Zane", LocalDate.of(1994, 8, 16), "robzane@mail.fr"), employeesList.get(0));
    }

    @Test
    public void shouldReadEmployeesWithFileDataSource() {
        FileEmployeeDataSource fileEmployeeDataSource = new FileEmployeeDataSource("src/test/java/com/tdd/app/employees.csv");
        EmployeeRepository employeeRepository = new EmployeeDataSourceRepository(fileEmployeeDataSource);

        List<Employee> employeesList = employeeRepository.getEmployees();

        assertEquals(2, employeesList.size());
        assertEquals(new Employee("Doe", "John", LocalDate.of(1982, 10, 8), "john.doe@foobar.com"), employeesList.get(0));
        assertEquals(new Employee("Ann", "Mary", LocalDate.of(1975, 9, 11), "mary.ann@foobar.com"), employeesList.get(1));
    }

}
