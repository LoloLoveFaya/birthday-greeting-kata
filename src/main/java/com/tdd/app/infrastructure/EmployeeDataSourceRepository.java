package com.tdd.app.infrastructure;

import com.tdd.app.domain.Employee;
import com.tdd.app.domain.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDataSourceRepository implements EmployeeRepository {

    private List<Employee> employeeList;

    public EmployeeDataSourceRepository(EmployeeDataSource dataSource) {
        this.employeeList = dataSource.read();
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeList;
    }

    @Override
    public List<Employee> findEmployeesBornOn(int month, int day) {
        LocalDate dateof = LocalDate.of(LocalDate.now().getYear(), month, day);

        return getEmployees().stream()
                .filter(employee -> employee.isBirthdayOn(dateof))
                .collect(Collectors.toList());
    }
}
