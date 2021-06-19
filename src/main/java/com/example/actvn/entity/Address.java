package com.example.actvn.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name", nullable = false, length = 500)
    private String name;
    @Basic
    @Column(name = "is_active", nullable = true)
    private long isActive;

}
