package com.example.actvn.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "attendance")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Attendance {
    @Id
    @Column(name = "id", nullable = false,insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "user_id", nullable = false)
    private long userId;
    @Basic
    @Column(name = "schedule_id", nullable = false)
    private long scheduleId;
    @Basic
    @Column(name = "present", nullable = false)
    private Integer present;
    @Basic
    @Column(name = "note", nullable = true, length = 500)
    private String note;
    @Basic
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at", nullable = false, insertable = false)
    private Timestamp updatedAt;
    @Basic
    @Column(name = "is_active", nullable = false, insertable = false)
    private long isActive;
    @Basic
    @Column(name = "imei", nullable = false)
    private String imei;

}
