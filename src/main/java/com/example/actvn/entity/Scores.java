package com.example.actvn.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Scores {
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "classroom_id", nullable = false)
    private long classroomId;
    @Basic
    @Column(name = "user_id", nullable = false)
    private long userId;
    @Basic
    @Column(name = "type", nullable = false)
    private int type;
    @Basic
    @Column(name = "point", nullable = false, precision = 0)
    private double point;
    @Basic
    @Column(name = "is_active", nullable = false, insertable = false)
    private int isActive;
    @Basic
    @Column(name = "created_at", nullable = false, insertable = false,updatable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at", nullable = false, insertable = false)
    private Timestamp updatedAt;

}
