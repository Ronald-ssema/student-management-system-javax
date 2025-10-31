package com.sms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // match repository queries
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "offering_id", nullable = false)
    private Long offeringId;

    public Enrollment() {}

    public Enrollment(Long studentId, Long offeringId) {
        this.studentId = studentId;
        this.offeringId = offeringId;
    }

    public Long getId() { return id; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getOfferingId() { return offeringId; }
    public void setOfferingId(Long offeringId) { this.offeringId = offeringId; }
}
