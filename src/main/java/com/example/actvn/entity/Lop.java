package com.example.actvn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "Lop")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Lop {
    @Id
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "ten_lop", nullable = true, length = 10)
    private String tenLop;
    @Basic
    @Column(name = "hieu_luc", nullable = true, length = 1, insertable = false)
    private String hieuLuc;
    @Basic
    @Column(name = "ma", nullable = true, length = 20)
    private String ma;
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
    @Column(name = "khoa", nullable = true, length = 10)
    private String khoa;
    @Basic
    @Column(name = "khoa_ID", nullable = true)
    private Integer khoaId;
    @Basic
    @Column(name = "chuyen_nganh_ID", nullable = true)
    private Integer chuyenNganhId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Ds_lop",
            joinColumns = @JoinColumn(name = "lop_ID"),
            inverseJoinColumns = @JoinColumn(name = "sinh_vien_ID"))
    private Set<Account> sinhVien = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Ds_lop",
            joinColumns = @JoinColumn(name = "lop_ID"),
            inverseJoinColumns = @JoinColumn(name = "giao_vien_chu_nhiem_ID"))
    private Set<Account> giaoVien = new HashSet<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "khoa_ID", referencedColumnName = "id", nullable = false)
////    @JsonIgnore
//    private DmHeThong dmKhoa;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "chuyen_nganh_ID", referencedColumnName = "id", nullable = false)
////    @JsonIgnore
//    private DmHeThong dmChuyenNganh;
}
