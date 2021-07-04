package com.tdd.app.domain;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getEmployees();

    List<Employee> findEmployeesBornOn(int month, int day);
}
