package com.example.actvn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name="classroom")
public class Classroom {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "name", nullable = false, length = 500)
    private String name;
//    @Basic
//    @Column(name = "subject_id", nullable = false)
//    private long subjectId;
    @Basic
    @Column(name = "is_active", nullable = true)
    private long active;
    @Basic
    @Column(name = "_name", nullable = true, length = 500)
    private String nameKD;

    @ManyToOne
    @JoinColumn(name = "subject_id",nullable = false)
    private Subject subject;


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "classroom_user",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Collection<User> users;
}
