package com.sms.service.impl;

import com.sms.entity.Enrollment;
import com.sms.repository.EnrollmentRepository;
import com.sms.service.EnrollmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository repo;

    public EnrollmentServiceImpl(EnrollmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return repo.findAll();
    }

    @Override
    public Enrollment getEnrollmentByStudentAndOffering(Long studentId, Long offeringId) {
        // Repo returns List<Enrollment>
        List<Enrollment> found = repo.findByStudentIdAndOfferingId(studentId, offeringId);
        if (found == null || found.isEmpty()) {
            throw new IllegalArgumentException("Enrollment not found: studentId=" + studentId + ", offeringId=" + offeringId);
        }
        return found.get(0);
    }

    @Override
    public Enrollment createEnrollment(Enrollment enrollment) {
        return repo.save(enrollment);
    }
}
