package com.matrix.simpleresultsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "tets")
@Data
public class StudentResult {


    @Id
    @Column(name = "is_nomresi")
    private Long jobNumber;
    // artıq primary key, identity və unique

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

    // getters və setters
}
