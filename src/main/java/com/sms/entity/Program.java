package com.sms.entity;

import jakarta.persistence.*;

@Entity
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;    // e.g., "BSc Computer Science"
    private String code;    // e.g., "BSC-CS"

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Program() {}

    public Program(String name, String code, Department department) {
        this.name = name;
        this.code = code;
        this.department = department;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", department=" + (department != null ? department.getName() : "None") +
                '}';
    }
}

