package com.matrix.simpleresultsystem.service;

import com.matrix.simpleresultsystem.entity.StudentResult;
import com.matrix.simpleresultsystem.repository.StudentResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentResultService {

    private final StudentResultRepository repository;

    public StudentResultService(StudentResultRepository repository) {
        this.repository = repository;
    }

//    public List<StudentResult> getAllResults() {
//        return repository.findAll();
//    }

    public StudentResult getByJobNumber(Long jobNumber) {
        System.out.println(jobNumber);
        return repository.findByJobNumber(jobNumber)
                .orElseThrow(() -> new RuntimeException("Student not found with jobNumber: " + jobNumber));
    }

//    public StudentResult saveResult(StudentResult result) {
//        return repository.save(result);
//    }

//    public void deleteResult(Long id) {
//        repository.deleteById(id);
//    }
}

