package com.example.actvn.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "index", nullable = true)
    private Integer index;
    @Basic
    @Column(name = "start_time", nullable = false)
    private long startTime;
    @Basic
    @Column(name = "end_time", nullable = false)
    private long endTime;

}
