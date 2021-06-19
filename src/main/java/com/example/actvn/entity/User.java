package com.example.actvn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "username", nullable = false, length = 500)
    private String userName;
    @Basic
    @JsonIgnore
    @Column(name = "password", nullable = false, length = 500)
    private String password;
    //    @Basic
//    @Column(name = "role_id", nullable = false)
//    private long roleId;
    @Basic
    @Column(name = "name", nullable = true, length = 500)
    private String name;
    @Basic
    @Column(name = "is_active", nullable = true)
    private long active;
    @Basic
    @Column(name = "avatar", nullable = true, length = 500)
    private String avatar;
    @Basic
    @Column(name = "_name", nullable = true, length = 500)
    private String nameKD;
    @Basic
    @Column(name = "device_token", nullable = true, length = 500)
    private String deviceToken;

    @ManyToOne
    @JoinColumn(nullable = false, name = "role_id")
    private Role role;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "classroom_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id")
    )
    @Where(clause = "is_active = 1")
    private Set<Classroom> classrooms = new HashSet<>();
}
