package com.matrix.simpleresultsystem.controller;

import com.matrix.simpleresultsystem.entity.StudentResult;
import com.matrix.simpleresultsystem.service.StudentResultService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = "*")
public class StudentResultController {

    private final StudentResultService service;

    public StudentResultController(StudentResultService service) {
        this.service = service;
    }

//    @GetMapping
//    public List<StudentResult> getAll() {
//        return service.getAllResults();
//    }

    @GetMapping("/{jobNumber}")
    public StudentResult getByJobNumber(@PathVariable Long jobNumber) {
        return service.getByJobNumber(jobNumber);
    }

//    @PostMapping
//    public StudentResult create(@RequestBody StudentResult result) {
//        return service.saveResult(result);
//    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        service.deleteResult(id);
//    }
}

