package com.example.actvn.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Schedule {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "start_time", nullable = true, length = 500)
    private String startTime;
    @Basic
    @Column(name = "end_time", nullable = true, length = 500)
    private String endTime;
    @Basic
    @Column(name = "start_lesson", nullable = true)
    private Long startLesson;
    @Basic
    @Column(name = "end_lesson", nullable = true)
    private Long endLesson;
    @Basic
    @Column(name = "datetime", nullable = false)
    private Timestamp datetime;
    @Basic
    @Column(name = "date", nullable = true)
    private Integer date;
    @Basic
    @Column(name = "year", nullable = true)
    private Long year;
    @Basic
    @Column(name = "month", nullable = true)
    private Long month;
    @Basic
    @Column(name = "classroom_name", nullable = true, length = 500)
    private String classroomName;
    @Basic
    @Column(name = "_classroom_name", nullable = true, length = 500)
    private String classroomNameKD;
    @Basic
    @Column(name = "address_id", nullable = true)
    private Long addressId;
    @Basic
    @Column(name = "is_active", nullable = true)
    private Byte isActive;
    @Basic
    @Column(name = "start_lesson_id", nullable = false)
    private long startLessonId;
    @Basic
    @Column(name = "end_lesson_id", nullable = true)
    private Long endLessonId;
    @Basic
    @Column(name = "address_name", nullable = true, length = 500)
    private String addressName;
    @Basic
    @Column(name = "classroom_id", nullable = false)
    private long classroomId;

}
