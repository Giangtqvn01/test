package com.example.actvn.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Lop {
    private int id;
    private String tenLop;
    private String hieuLuc;
    private String ma;
    private String nguoiTao;
    private Timestamp ngayTao;
    private String nguoiSuaCuoi;
    private Timestamp ngaySuaCuoi;
    private String khoa;
    private Integer khoaId;
    private Integer chuyenNganhId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ten_lop", nullable = true, length = 10)
    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    @Basic
    @Column(name = "hieu_luc", nullable = true, length = 1)
    public String getHieuLuc() {
        return hieuLuc;
    }

    public void setHieuLuc(String hieuLuc) {
        this.hieuLuc = hieuLuc;
    }

    @Basic
    @Column(name = "ma", nullable = true, length = 20)
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
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
    @Column(name = "ngay_tao", nullable = true)
    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
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
    @Column(name = "khoa", nullable = true, length = 10)
    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    @Basic
    @Column(name = "khoa_ID", nullable = true)
    public Integer getKhoaId() {
        return khoaId;
    }

    public void setKhoaId(Integer khoaId) {
        this.khoaId = khoaId;
    }

    @Basic
    @Column(name = "chuyen_nganh_ID", nullable = true)
    public Integer getChuyenNganhId() {
        return chuyenNganhId;
    }

    public void setChuyenNganhId(Integer chuyenNganhId) {
        this.chuyenNganhId = chuyenNganhId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lop lop = (Lop) o;
        return id == lop.id && Objects.equals(tenLop, lop.tenLop) && Objects.equals(hieuLuc, lop.hieuLuc) && Objects.equals(ma, lop.ma) && Objects.equals(nguoiTao, lop.nguoiTao) && Objects.equals(ngayTao, lop.ngayTao) && Objects.equals(nguoiSuaCuoi, lop.nguoiSuaCuoi) && Objects.equals(ngaySuaCuoi, lop.ngaySuaCuoi) && Objects.equals(khoa, lop.khoa) && Objects.equals(khoaId, lop.khoaId) && Objects.equals(chuyenNganhId, lop.chuyenNganhId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenLop, hieuLuc, ma, nguoiTao, ngayTao, nguoiSuaCuoi, ngaySuaCuoi, khoa, khoaId, chuyenNganhId);
    }
}
