package com.example.actvn.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Subject {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "name", nullable = true, length = 500)
    private String name;
    @Basic
    @Column(name = "number_of_credits", nullable = true)
    private Integer numberOfCredits;
    @Basic
    @Column(name = "is_active", nullable = true)
    private Byte isActive;
    @Basic
    @Column(name = "study_form", nullable = true, length = 500)
    private String studyForm;
    @Basic
    @Column(name = "_name", nullable = true, length = 500)
    private String nameKD;

}
