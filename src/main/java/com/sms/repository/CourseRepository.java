package com.sms.repository;

import com.sms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByDepartments_Id(Long departmentId);
    boolean existsByTitle(String title);
default boolean existsByCode(String code) { return existsByTitle(code); }
}
