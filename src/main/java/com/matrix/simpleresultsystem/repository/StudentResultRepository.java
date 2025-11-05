package com.matrix.simpleresultsystem.repository;

import com.matrix.simpleresultsystem.entity.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface StudentResultRepository extends JpaRepository<StudentResult, Long> {

    @Query("SELECT s FROM StudentResult s WHERE s.examId = :examId AND s.jobNumber = :jobNumber")
    Optional<StudentResult> findByExamAndJobNumber(@Param("examId") Long examId,
                                                   @Param("jobNumber") Long jobNumber);

    @Query("SELECT s FROM StudentResult s WHERE " +
            "s.examId = :examId AND " +
            "LOWER(TRIM(s.name)) = LOWER(TRIM(:name)) AND " +
            "LOWER(TRIM(s.surname)) = LOWER(TRIM(:surname)) AND " +
            "LOWER(TRIM(s.fatherName)) = LOWER(TRIM(:fatherName))")
    Optional<StudentResult> findByFullNameAndExamIdIgnoreCase(@Param("examId") Long examId,
                                                              @Param("name") String name,
                                                              @Param("surname") String surname,
                                                              @Param("fatherName") String fatherName);

    List<StudentResult> findAllByExamId(Long examId);
}
