package com.example.actvn.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@Table(name = "dm_khoa")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DmKhoa {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "KHOA_ID", nullable = false)
    private int khoaId;
    @Basic
    @Column(name = "Ma", nullable = false, length = 20)
    private String ma;
    @Basic
    @Column(name = "Ten", nullable = false, length = 250)
    private String ten;
    @Basic
    @Column(name = "mo_ta", nullable = true, length = 250)
    private String moTa;
    @Basic
    @Column(name = "hieu_luc", nullable = false, length = 250)
    private String hieuLuc;
    @Basic
    @Column(name = "nguoi_tao", nullable = false, length = 100)
    private String nguoiTao;
    @Basic
    @Column(name = "nguoi_sua_cuoi", nullable = true, length = 100)
    private String nguoiSuaCuoi;
    @Basic
    @Column(name = "ngay_tao", nullable = false)
    private Timestamp ngayTao;
    @Basic
    @Column(name = "ngay_sua_cuoi", nullable = false)
    private Timestamp ngaySuaCuoi;

}
