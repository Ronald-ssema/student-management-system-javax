package com.sms.service.impl;

import com.sms.entity.Department;
import com.sms.exception.NotFoundException;
import com.sms.repository.DepartmentRepository;
import com.sms.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repo;

    public DepartmentServiceImpl(DepartmentRepository repo) { this.repo = repo; }

     public List<Department> getAll() { return repo.findAll(); }

     public Department getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Department not found: " + id));
    }

     public Department create(Department d) { return repo.save(d); }

     public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Department not found: " + id);
        repo.deleteById(id);
    }
}
