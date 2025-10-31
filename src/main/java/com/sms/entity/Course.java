package com.sms.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // existing field
    private String title;

    // Inverse side must match Department.courses
    @ManyToMany(mappedBy = "courses")
    private Set<Department> departments = new HashSet<>();

    public Course() {}

    public Course(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    // Compatibility with code that expects getName()/setName()
    public String getName() { return title; }
    public void setName(String name) { this.title = name; }

    public Set<Department> getDepartments() { return departments; }
    public void setDepartments(Set<Department> departments) { this.departments = departments; }
}
