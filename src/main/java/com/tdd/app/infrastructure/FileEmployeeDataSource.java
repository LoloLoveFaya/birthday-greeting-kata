package com.tdd.app.infrastructure;

import com.tdd.app.domain.Employee;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileEmployeeDataSource implements EmployeeDataSource {
    final String COMMA_DELIMITER = ",";

    private String filePath;

    public FileEmployeeDataSource(String filePath) {
        this.filePath = filePath;
    }

    public List<Employee> read() {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return records.stream()
                .map(row -> new Employee(row.get(0), row.get(1), columnToDate(row.get(2)), row.get(3)))
                .collect(Collectors.toList());
    }

    private LocalDate columnToDate(String column) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(column, formatter);
    }
}
