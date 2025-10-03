package com.matrix.simpleresultsystem.controller;

import com.matrix.simpleresultsystem.entity.StudentResult;
import com.matrix.simpleresultsystem.service.StudentResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/find-job-number")
    public ResponseEntity<?> findJobNumber(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String fatherName) {

        return service.findByFullName(name, surname, fatherName)
                .map(student -> ResponseEntity.ok(Map.of(
                        "jobNumber", student.getJobNumber(),
                        "fullName", student.getSurname() + " " + student.getName(),
                        "grade", student.getGrade()
                )))
                .orElse(ResponseEntity.status(404)
                        .body(Map.of("message", "Daxil etdiyiniz məlumatlara uyğun şagird tapılmadı.")));
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

