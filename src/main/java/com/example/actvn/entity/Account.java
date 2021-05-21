package com.example.actvn.entity;

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
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Account {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "tai_khoan", nullable = false, length = 10)
    private String taiKhoan;
    @Basic
    @Column(name = "mat_khau", nullable = false, length = 255)
    private String password;
    @Basic
    @Column(name = "ma", nullable = true, length = 20)
    private String ma;
    @Basic
    @Column(name = "role_cd", nullable = false, length = 64)
    private String roleCd;
    @Basic
    @Column(name = "ho_va_ten", nullable = true, length = 100)
    private String hoVaTen;
    @Basic
    @Column(name = "ngay_sinh", nullable = true)
    private Timestamp ngaySinh;
    @Basic
    @Column(name = "email", nullable = true, length = 100)
    private String email;
    @Basic
    @Column(name = "dien_thoai_ca_nhan", nullable = true, length = 10)
    private String dienThoaiCaNhan;
    @Basic
    @Column(name = "dien_thoai_nguoi_than", nullable = true, length = 10)
    private String dienThoaiNguoiThan;
    @Basic
    @Column(name = "hieu_luc", nullable = true, length = 1, insertable = false)
    private String hieuLuc;
    @Basic
    @Column(name = "nguoi_tao", nullable = true, length = 100)
    private String nguoiTao;
    @Basic
    @Column(name = "nguoi_sua_cuoi", nullable = true, length = 100)
    private String nguoiSuaCuoi;
    @Basic
    @Column(name = "ngay_tao", nullable = true, insertable = false, updatable = false)
    private Timestamp ngayTao;
    @Basic
    @Column(name = "ngay_sua_cuoi", nullable = true, insertable = false)
    private Timestamp ngaySuaCuoi;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_user",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
