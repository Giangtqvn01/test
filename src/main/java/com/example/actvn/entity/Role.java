package com.example.actvn.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name", nullable = false, length = 500)
    private String name;
    @Basic
    @Column(name = "code", nullable = false, length = 500)
    private String code;

}
