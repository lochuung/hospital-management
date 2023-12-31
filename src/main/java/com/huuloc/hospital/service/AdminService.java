package com.huuloc.hospital.service;

import com.huuloc.hospital.entity.Employee;

import java.util.List;

public interface AdminService {
    void addEmployee(Employee employee);
    void deleteEmployee(long id);
    void updateEmployee(long id, Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployee(long id);
    boolean checkEmployeeExists(Employee employee);
    boolean checkEmployeeExists(long id);
}
