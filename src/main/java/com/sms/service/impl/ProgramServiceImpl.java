package com.sms.service.impl;

import com.sms.entity.Program;
import com.sms.exception.NotFoundException;
import com.sms.repository.ProgramRepository;
import com.sms.service.ProgramService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository repo;

    public ProgramServiceImpl(ProgramRepository repo) { this.repo = repo; }

     public List<Program> getAll() { return repo.findAll(); }

     public Program getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Program not found: " + id));
    }

     public Program create(Program p) { return repo.save(p); }

     public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Program not found: " + id);
        repo.deleteById(id);
    }
}
