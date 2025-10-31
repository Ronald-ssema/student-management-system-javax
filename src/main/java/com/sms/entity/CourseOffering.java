package com.sms.entity;

import jakarta.persistence.*;

@Entity
public class CourseOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "term_id")
    private Term term;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    private String instructorName; // simple for now
    private int capacity;

    public CourseOffering() {}

    public CourseOffering(Course course, Term term, Classroom classroom, String instructorName, int capacity) {
        this.course = course;
        this.term = term;
        this.classroom = classroom;
        this.instructorName = instructorName;
        this.capacity = capacity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "CourseOffering{" +
                "id=" + id +
                ", course=" + (course != null ? course.getName() : "None") +
                ", term=" + (term != null ? term.getName() : "None") +
                ", classroom=" + (classroom != null ? classroom.getRoomNumber() : "None") +
                ", instructorName='" + instructorName + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
