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

//    public List<StudentResult> getAllResults() {
//        return repository.findAll();
//    }

    public StudentResult getByJobNumber(Long jobNumber) {
        Optional<StudentResult> optionalResult = repository.findByJobNumber(jobNumber);

        // Nəticəni logla
        optionalResult.ifPresentOrElse(
                result -> System.err.println(result.toString()),
                () -> System.err.println(" ❌ Student not found with jobNumber: " + jobNumber)
        );

        // Nəticəni qaytar və ya exception at
        return optionalResult.orElseThrow(
                () -> new RuntimeException("❌ Student not found with jobNumber: " + jobNumber)
        );
    }


    public Optional<StudentResult> findByFullName(String name, String surname, String fatherName) {
        log.info("Ad-soyada görə axtarış: ad='{}', soyad='{}', ata adı='{}'",
                name, surname, fatherName);

        Optional<StudentResult> result = repository.findByFullNameIgnoreCase(name, surname, fatherName);

        if (result.isPresent()) {
            log.info("✅ Tapıldı: {} {} — İş nömrəsi: {}",
                    result.get().getSurname(),
                    result.get().getName(),
                    result.get().getJobNumber());
        } else {
            log.warn("❌ Tapılmadı: {} {} ({})", surname, name, fatherName);
        }

        return result;
    }

}


