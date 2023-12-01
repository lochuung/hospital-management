package com.huuloc.hospital.service.impl;

import com.huuloc.hospital.entity.Employee;
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
}
