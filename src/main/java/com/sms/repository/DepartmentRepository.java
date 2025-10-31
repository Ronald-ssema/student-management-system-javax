package com.sms.repository;

import com.sms.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name);
default boolean existsByCode(String code) { return existsByName(code); }
}
