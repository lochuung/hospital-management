package com.huuloc.hospital.service.impl;

import com.huuloc.hospital.entity.*;
import com.huuloc.hospital.repository.EmployeeRepository;
import com.huuloc.hospital.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AdminServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public long addEmployee(Employee employee) {
        if (employeeRepository.findByUsername(employee.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken!");
        }
        Employee oldEmployee = employee;
        switch (employee.getType()) {
            case "Doctor":
                employee = new Doctor();
                break;
            case "Admin":
                employee = new Admin();
                break;
            case "Pharmacist":
                employee = new Pharmacist();
                break;
            case "Receptionist":
                employee = new Receptionist();
                break;
            case "Accountant":
                employee = new Accountant();
                break;
            default:
                throw new IllegalArgumentException("Invalid employee type!");
        }
        employee.setFullName(oldEmployee.getFullName());
        employee.setEmail(oldEmployee.getEmail());
        employee.setGender(oldEmployee.getGender());
        employee.setUsername(oldEmployee.getUsername());
        employee.setPassword(oldEmployee.getPassword());
        employee.setAddress(oldEmployee.getAddress());
        employee.setPhone(oldEmployee.getPhone());
        employee.setDob(oldEmployee.getDob());

        employeeRepository.save(employee);
        return employee.getId();
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public long updateEmployee(long id, Employee employee) {
        Employee old = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));

        old.setFullName(employee.getFullName());
        old.setEmail(employee.getEmail());
        old.setGender(employee.getGender());
        old.setUsername(employee.getUsername());
        old.setPassword(employee.getPassword());
        old.setAddress(employee.getAddress());
        old.setPhone(employee.getPhone());
        old.setDob(employee.getDob());
        old.setType(employee.getType());

        employeeRepository.save(old);
        return old.getId();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
    }

    @Override
    public boolean checkEmployeeExists(Employee employee) {
        if (employeeRepository.findByUsername(employee.getUsername()).isPresent()) {
            return true;
        }
        return employeeRepository.findByEmail(employee.getEmail()).isPresent();
    }

    @Override
    public boolean checkEmployeeExists(long id) {
        return employeeRepository.findById(id).isPresent();
    }
}
