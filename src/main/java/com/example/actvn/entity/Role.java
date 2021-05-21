package com.example.actvn.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity(name = "role")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Role {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "role_cd")
    private RoleName roleCd;
    @Basic
    @Column(name = "ten", nullable = true, length = 250)
    private String ten;
    @Basic
    @Column(name = "mo_ta", nullable = true, length = 250)
    private String moTa;
    @Basic
    @Column(name = "nguoi_tao", nullable = true, length = 250)
    private String nguoiTao;
    @Basic
    @Column(name = "ngay_tao", nullable = true)
    private Timestamp ngayTao;
    @Basic
    @Column(name = "nguoi_sua_cuoi", nullable = true, length = 250)
    private String nguoiSuaCuoi;
    @Basic
    @Column(name = "ngay_sua_cuoi", nullable = true)
    private Timestamp ngaySuaCuoi;
    @Basic
    @Column(name = "hieu_luc", nullable = true, length = 1)
    private String hieuLuc;

}
