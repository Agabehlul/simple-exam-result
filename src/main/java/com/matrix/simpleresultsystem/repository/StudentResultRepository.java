package com.matrix.simpleresultsystem.repository;

import com.matrix.simpleresultsystem.entity.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentResultRepository extends JpaRepository<StudentResult, Long> {
    Optional<StudentResult> findByJobNumber(Long jobNumber);
}

