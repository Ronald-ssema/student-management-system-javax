package com.sms.controller;

import com.sms.entity.Enrollment;
import com.sms.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return service.getAllEnrollments();
    }

    // /api/enrollments/by?studentId=1&offeringId=2
    @GetMapping("/by")
    public ResponseEntity<Enrollment> getByStudentAndOffering(@RequestParam Long studentId,
                                                              @RequestParam Long offeringId) {
        return ResponseEntity.ok(service.getEnrollmentByStudentAndOffering(studentId, offeringId));
    }

    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return service.createEnrollment(enrollment);
    }
}
