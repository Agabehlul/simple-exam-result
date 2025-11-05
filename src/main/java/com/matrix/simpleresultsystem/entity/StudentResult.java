package com.matrix.simpleresultsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // unikal ID hər nəticə üçün

    @Column(name = "exam_id")
    private Long examId; // yeni sütun — imtahan ID

    @Column(name = "is_nomresi")
    private Long jobNumber;

    @Column(name = "soyad")
    private String surname;

    @Column(name = "ad")
    private String name;

    @Column(name = "ata_adi")
    private String fatherName;

    @Column(name = "variant")
    private String variant;

    @Column(name = "sinif")
    private String grade;

    @Column(name = "duz_sayi")
    private Integer correctCount;

    @Column(name = "yazi_isi")
    private String writingWork;

    @Column(name = "yekun")
    private String total;
}
