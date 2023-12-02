package com.huuloc.hospital.repository;

import com.huuloc.hospital.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DrugRepository extends JpaRepository<Drug, Long> {
}
