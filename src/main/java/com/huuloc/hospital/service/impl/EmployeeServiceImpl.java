package com.huuloc.hospital.service.impl;

import com.huuloc.hospital.entity.Employee;
import com.huuloc.hospital.repository.EmployeeRepository;
import com.huuloc.hospital.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Employee not found with id " + id)
        );
    }
}
