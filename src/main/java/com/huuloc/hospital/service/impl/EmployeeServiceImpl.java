package com.huuloc.hospital.service.impl;

import com.huuloc.hospital.entity.Employee;
import com.huuloc.hospital.repository.EmployeeRepository;
import com.huuloc.hospital.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

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

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("Employee not found with username " + username)
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Employee not found with username "
                        + username)
        );
        return new User(employee.getUsername(), employee.getPassword(),
                getAuthorities(employee));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Employee employee) {
        String role = "ROLE_" +  employee.getType().toUpperCase();
        return AuthorityUtils.createAuthorityList(role);
    }
}
