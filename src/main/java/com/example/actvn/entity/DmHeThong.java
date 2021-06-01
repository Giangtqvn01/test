package com.example.actvn.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@Table(name = "dm_he_thong")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DmHeThong {
    @Id
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "loai_danh_muc", nullable = true, length = 100)
    private String loaiDanhMuc;
    @Basic
    @Column(name = "ten_loai_danh_muc", nullable = true, length = 100)
    private String tenLoaiDanhMuc;
    @Basic
    @Column(name = "ma", nullable = true, length = 30)
    private String ma;
    @Basic
    @Column(name = "ten", nullable = true, length = 100)
    private String ten;
    @Basic
    @Column(name = "mo_ta", nullable = true, length = 100)
    private String moTa;
    @Basic
    @Column(name = "hieu_luc", nullable = true, length = 1)
    private String hieuLuc;
    @Basic
    @Column(name = "do_uu_tien", nullable = true)
    private Integer doUuTien;
    @Basic
    @Column(name = "nguoi_tao", nullable = true, length = 100)
    private String nguoiTao;
    @Basic
    @Column(name = "ngay_tao", nullable = true)
    private Timestamp ngayTao;
    @Basic
    @Column(name = "nguoi_sua_cuoi", nullable = true, length = 100)
    private String nguoiSuaCuoi;
    @Basic
    @Column(name = "ngay_sua_cuoi", nullable = true)
    private Timestamp ngaySuaCuoi;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "khoa_ID", nullable = false, insertable = false, updatable = false)
    private DmKhoa khoa;
}
