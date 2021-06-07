package com.example.actvn.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@Table(name = "thoi_gian_hoc_phan")
public class ThoiGianHocPhan {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "ngay_hoc", nullable = true)
    private Timestamp ngayHoc;
    @Basic
    @Column(name = "dia_diem", nullable = true, length = 10)
    private String diaDiem;
    @Basic
    @Column(name = "mon_hoc_ID", nullable = true)
    private Integer monHocId;
    @Basic
    @Column(name = "ghi_chu", nullable = true, length = 250)
    private String ghiChu;
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
