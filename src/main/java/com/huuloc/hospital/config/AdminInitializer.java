package com.huuloc.hospital.config;

import com.huuloc.hospital.entity.Admin;
import com.huuloc.hospital.entity.Employee;
import com.huuloc.hospital.repository.EmployeeRepository;
import com.huuloc.hospital.util.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements ApplicationRunner {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminInitializer(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String username = "admin";
        String password = "admin";
        String email = "admin@gmail.com";
        Gender gender = Gender.MALE;

        Employee employee = employeeRepository.findByUsername(username)
                .orElse(null);
        if (employee == null) {
            employee = new Admin();
            employee.setFullName(username);
            employee.setUsername(username);
            employee.setPassword(
                    passwordEncoder.encode(password)
            );
            employee.setEmail(email);
            employee.setGender(gender);
            employeeRepository.save(employee);
        }
    }
}
