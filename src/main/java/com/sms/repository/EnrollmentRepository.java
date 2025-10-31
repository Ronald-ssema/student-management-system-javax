package com.sms.repository;

import com.sms.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("SELECT e FROM Enrollment e WHERE e.studentId = :studentId")
    List<Enrollment> findByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT e FROM Enrollment e WHERE e.studentId = :studentId AND e.offeringId = :offeringId")
    List<Enrollment> findByStudentIdAndOfferingId(@Param("studentId") Long studentId,
                                                  @Param("offeringId") Long offeringId);
}
