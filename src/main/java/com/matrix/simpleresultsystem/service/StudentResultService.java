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
        log.info("🟢 StudentResultService yaradıldı, repository inject edildi.");
    }

    // Exam və İş nömrəsinə görə nəticə
    public StudentResult getByExamAndJobNumber(Long examId, Long jobNumber) {
        log.info("🔍 Nəticə axtarılır: Exam ID = {}, İş nömrəsi = {}", examId, jobNumber);

        Optional<StudentResult> optionalResult = repository.findByExamAndJobNumber(examId, jobNumber);

        optionalResult.ifPresentOrElse(
                result -> {
                    log.info("✅ Tapıldı: {} {} | Exam ID: {}, İş nömrəsi: {}",
                            result.getSurname(), result.getName(), examId, jobNumber);
                    log.info("🏆 Yekun bal: {}", result.getTotal()); // Yekun balı logla
                },
                () -> log.warn("❌ Tapılmadı: Exam ID {}, İş nömrəsi {}", examId, jobNumber)
        );

        return optionalResult.orElseThrow(
                () -> new RuntimeException("❌ Nəticə tapılmadı (Exam ID: " + examId + ", İş nömrəsi: " + jobNumber + ")")
        );
    }

    // Ad-soyad və ata adına görə nəticə
    public Optional<StudentResult> findByFullNameAndExamId(Long examId, String name, String surname, String fatherName) {
        log.info("🔍 Ad-soyada görə axtarış: ad='{}', soyad='{}', ata adı='{}', examId={}",
                name, surname, fatherName, examId);

        Optional<StudentResult> result = repository.findByFullNameAndExamIdIgnoreCase(examId, name, surname, fatherName);

        result.ifPresentOrElse(
                r -> log.info("✅ Tapıldı: {} {} ({}) — İş nömrəsi: {} ",
                        r.getSurname(), r.getName(), fatherName, r.getJobNumber()),
                () -> log.warn("❌ Tapılmadı: {} {} ({}) | examId={}", surname, name, fatherName, examId)
        );

        return result;
    }

    // Yeni nəticə əlavə et və logla
//    public StudentResult saveResult(StudentResult result) {
//        log.info("💾 Yeni nəticə əlavə olunur: {} {} ({}) — Exam ID: {}, İş nömrəsi: {}",
//                result.getSurname(), result.getName(), result.getFatherName(), result.getExamId(), result.getJobNumber());
//
//        StudentResult savedResult = repository.save(result);
//
//        log.info("✅ Nəticə saxlanıldı: ID = {} | 🏆 Yekun bal: {}", savedResult.getId(), savedResult.getTotalScore());
//        return savedResult;
//    }

    // Bütün exam üçün nəticələr
//    public List<StudentResult> getAllByExam(Long examId) {
//        log.info("📄 Bütün nəticələr axtarılır: Exam ID = {}", examId);
//
//        List<StudentResult> results = repository.findAllByExamId(examId);
//
//        log.info("📊 {} nəticə tapıldı Exam ID = {}", results.size(), examId);
//        results.forEach(r -> log.info("🎯 {} {} — İş nömrəsi: {} | 🏆 Yekun bal: {}",
//                r.getSurname(), r.getName(), r.getJobNumber(), r.getTotalScore()));
//
//        return results;
//    }


}
