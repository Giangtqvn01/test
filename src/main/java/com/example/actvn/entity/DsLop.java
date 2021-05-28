package com.example.actvn.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@Table(name = "ds_lop")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DsLop {
    @Id
    @Column(name = "ID", nullable = false,insertable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "thoi_gian_bat_dau", nullable = true)
    private Timestamp thoiGianBatDau;
    @Basic
    @Column(name = "thoi_gian_ket_thuc", nullable = true)
    private Timestamp thoiGianKetThuc;
    @Basic
    @Column(name = "sinh_vien_ID", nullable = true)
    private Integer sinhVienId;
    @Basic
    @Column(name = "giao_vien_chu_nhiem_ID", nullable = true)
    private Integer giaoVienChuNhiemId;
    @Basic
    @Column(name = "lop_ID", nullable = true)
    private Integer lopId;
    @Basic
    @Column(name = "hieu_luc", nullable = true, length = 1 , insertable = false)
    private String hieuLuc;

}
