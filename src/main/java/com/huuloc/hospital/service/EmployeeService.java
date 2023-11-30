package com.huuloc.hospital.service;

import com.huuloc.hospital.entity.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface EmployeeService extends UserDetailsService {
    Employee getEmployeeById(long id);
    Employee findByUsername(String username);
}
