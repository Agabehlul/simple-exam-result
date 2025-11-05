package com.matrix.simpleresultsystem.service;

import com.matrix.simpleresultsystem.entity.StudentResult;
import com.matrix.simpleresultsystem.repository.StudentResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentResultService {

    private final StudentResultRepository repository;

    public StudentResultService(StudentResultRepository repository) {
        this.repository = repository;
    }

    public StudentResult getByExamAndJobNumber(Long examId, Long jobNumber) {
        Optional<StudentResult> optionalResult = repository.findByExamAndJobNumber(examId, jobNumber);

        optionalResult.ifPresentOrElse(
                result -> log.info("✅ Tapıldı: {} {} — Exam ID: {}, İş nömrəsi: {}",
                        result.getSurname(), result.getName(), examId, jobNumber),
                () -> log.warn("❌ Tapılmadı: Exam ID {}, İş nömrəsi {}", examId, jobNumber)
        );

        return optionalResult.orElseThrow(
                () -> new RuntimeException("❌ Nəticə tapılmadı (Exam ID: " + examId + ", İş nömrəsi: " + jobNumber + ")")
        );
    }

    public Optional<StudentResult> findByFullNameAndExamId(Long examId, String name, String surname, String fatherName) {
        log.info("Ad-soyada görə axtarış: ad='{}', soyad='{}', ata adı='{}', examId={}",
                name, surname, fatherName, examId);

        Optional<StudentResult> result = repository.findByFullNameAndExamIdIgnoreCase(examId, name, surname, fatherName);

        if (result.isPresent()) {
            log.info("✅ Tapıldı: {} {} — İş nömrəsi: {}", result.get().getSurname(), result.get().getName(), result.get().getJobNumber());
        } else {
            log.warn("❌ Tapılmadı: {} {} ({}) | examId={}", surname, name, fatherName, examId);
        }

        return result;
    }

//    public List<StudentResult> getAllByExam(Long examId) {
//        return repository.findAllByExamId(examId);
//    }

//    public StudentResult saveResult(StudentResult result) {
//        return repository.save(result);
//    }
}
