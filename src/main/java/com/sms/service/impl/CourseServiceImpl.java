package com.sms.service.impl;

import com.sms.entity.Course;
import com.sms.exception.NotFoundException;
import com.sms.repository.CourseRepository;
import com.sms.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;

    public CourseServiceImpl(CourseRepository repo) { this.repo = repo; }

     public List<Course> getAll() { return repo.findAll(); }

     public Course getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Course not found: " + id));
    }

     public Course create(Course c) { return repo.save(c); }

     public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Course not found: " + id);
        repo.deleteById(id);
    }
}
