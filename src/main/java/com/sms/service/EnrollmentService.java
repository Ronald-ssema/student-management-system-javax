package com.sms.service;

import com.sms.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollments();

    // exact names/types used by controller + impl
    Enrollment getEnrollmentByStudentAndOffering(Long studentId, Long offeringId);

    Enrollment createEnrollment(Enrollment enrollment);
}
