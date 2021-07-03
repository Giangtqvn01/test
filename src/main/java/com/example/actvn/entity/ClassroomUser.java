package com.example.actvn.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "classroom_user")
public class ClassroomUser {
    @Basic
    @Column(name = "classroom_id", nullable = false)
    private long classroomId;
    @Basic
    @Column(name = "user_id", nullable = false)
    private long userId;
    @Basic
    @Column(name = "teacher_id")
    private long teacherId;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
