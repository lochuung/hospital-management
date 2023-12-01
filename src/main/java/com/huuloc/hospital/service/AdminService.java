package com.huuloc.hospital.service;

import com.huuloc.hospital.entity.Employee;

import java.util.List;

public interface AdminService {
    long addEmployee(Employee employee);
    void deleteEmployee(long id);
    long updateEmployee(long id, Employee employee);
    List<Employee> getAllEmployees();
}
