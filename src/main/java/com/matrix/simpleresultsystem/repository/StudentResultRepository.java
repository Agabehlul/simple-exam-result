package com.matrix.simpleresultsystem.repository;

import com.matrix.simpleresultsystem.entity.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentResultRepository extends JpaRepository<StudentResult, Long> {
    Optional<StudentResult> findByJobNumber(Long jobNumber);
    @Query("SELECT s FROM StudentResult s WHERE " +
            "LOWER(TRIM(s.name)) = LOWER(TRIM(:name)) AND " +
            "LOWER(TRIM(s.surname)) = LOWER(TRIM(:surname)) AND " +
            "LOWER(TRIM(s.fatherName)) = LOWER(TRIM(:fatherName))")
    Optional<StudentResult> findByFullNameIgnoreCase(@Param("name") String name,
                                                     @Param("surname") String surname,
                                                     @Param("fatherName") String fatherName);
}

