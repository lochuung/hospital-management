package com.huuloc.hospital.repository;

import com.huuloc.hospital.entity.Admin;
import com.huuloc.hospital.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
