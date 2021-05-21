package com.example.actvn.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "role_user", schema = "actvn", catalog = "")
public class RoleUser {
    private int id;
    private Integer accountId;
    private Integer roleId;
    private Timestamp ngayTao;
    private String nguoiTao;
    private String nguoiSuaCuoi;
    private Timestamp ngaySuaCuoi;
    private String hieuLuc;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "account_ID", nullable = true)
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "role_ID", nullable = true)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "ngay_tao", nullable = true)
    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Basic
    @Column(name = "nguoi_tao", nullable = true, length = 250)
    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    @Basic
    @Column(name = "nguoi_sua_cuoi", nullable = true, length = 250)
    public String getNguoiSuaCuoi() {
        return nguoiSuaCuoi;
    }

    public void setNguoiSuaCuoi(String nguoiSuaCuoi) {
        this.nguoiSuaCuoi = nguoiSuaCuoi;
    }

    @Basic
    @Column(name = "ngay_sua_cuoi", nullable = true)
    public Timestamp getNgaySuaCuoi() {
        return ngaySuaCuoi;
    }

    public void setNgaySuaCuoi(Timestamp ngaySuaCuoi) {
        this.ngaySuaCuoi = ngaySuaCuoi;
    }

    @Basic
    @Column(name = "hieu_luc", nullable = true, length = 10)
    public String getHieuLuc() {
        return hieuLuc;
    }

    public void setHieuLuc(String hieuLuc) {
        this.hieuLuc = hieuLuc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleUser roleUser = (RoleUser) o;
        return id == roleUser.id && Objects.equals(accountId, roleUser.accountId) && Objects.equals(roleId, roleUser.roleId) && Objects.equals(ngayTao, roleUser.ngayTao) && Objects.equals(nguoiTao, roleUser.nguoiTao) && Objects.equals(nguoiSuaCuoi, roleUser.nguoiSuaCuoi) && Objects.equals(ngaySuaCuoi, roleUser.ngaySuaCuoi) && Objects.equals(hieuLuc, roleUser.hieuLuc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, roleId, ngayTao, nguoiTao, nguoiSuaCuoi, ngaySuaCuoi, hieuLuc);
    }
}
