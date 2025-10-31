package com.sms.repository;

import com.sms.entity.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {
    List<CourseOffering> findByCourse_Id(Long courseId);
    List<CourseOffering> findByTermId(Long termId);
}
