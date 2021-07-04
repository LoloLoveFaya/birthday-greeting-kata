package com.tdd.app.infrastructure;

import com.tdd.app.domain.Employee;

import java.util.List;

public interface EmployeeDataSource {
    public List<Employee> read();
}
