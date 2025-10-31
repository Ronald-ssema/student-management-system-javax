package com.sms.service.impl;

import com.sms.entity.Student;
import com.sms.exception.NotFoundException;
import com.sms.repository.StudentRepository;
import com.sms.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) { this.repo = repo; }

     public List<Student> getAll() { return repo.findAll(); }

     public Student getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Student not found: " + id));
    }

     public Student create(Student s) { return repo.save(s); }

     public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Student not found: " + id);
        repo.deleteById(id);
    }
}
