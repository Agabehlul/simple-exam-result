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

    // 🔹 Bütün nəticələr (imtahana görə)
//    @GetMapping("/exam/{examId}")
//    public List<StudentResult> getAllByExam(@PathVariable Long examId) {
//        return service.getAllByExam(examId);
//    }

    // 🔹 İş nömrəsinə və imtahan ID-yə görə nəticə
    @GetMapping("/{examId}/{jobNumber}")
    public StudentResult getByExamAndJobNumber(@PathVariable Long examId,
                                               @PathVariable Long jobNumber) {
        return service.getByExamAndJobNumber(examId, jobNumber);
    }

    // 🔹 Ad, soyad, ata adına görə axtarış (imtahana görə)
    @GetMapping("/find-job-number")
    public ResponseEntity<?> findJobNumber(
            @RequestParam Long examId,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String fatherName) {

        return service.findByFullNameAndExamId(examId, name, surname, fatherName)
                .map(student -> ResponseEntity.ok(Map.of(
                        "examId", student.getExamId(),
                        "jobNumber", student.getJobNumber(),
                        "fullName", student.getSurname() + " " + student.getName(),
                        "grade", student.getGrade()
                )))
                .orElse(ResponseEntity.status(404)
                        .body(Map.of("message", "Daxil etdiyiniz məlumatlara uyğun şagird tapılmadı.")));
    }

    // 🔹 Yeni nəticə əlavə et (admin üçün)
//    @PostMapping
//    public StudentResult create(@RequestBody StudentResult result) {
//        return service.saveResult(result);
//    }
}
