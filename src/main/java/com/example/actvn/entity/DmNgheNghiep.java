package com.example.actvn.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "dm_nghe_nghiep", schema = "actvn", catalog = "")
public class DmNgheNghiep {
    private int id;
    private String loaiDanhMuc;
    private String tenLoaiDanhMuc;
    private String ma;
    private String ten;
    private String moTa;
    private String hieuLuc;
    private Integer doUuTien;
    private String nguoiTao;
    private Timestamp ngayTao;
    private String nguoiSuaCuoi;
    private Timestamp ngaySuaCuoi;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "loai_danh_muc", nullable = true, length = 100)
    public String getLoaiDanhMuc() {
        return loaiDanhMuc;
    }

    public void setLoaiDanhMuc(String loaiDanhMuc) {
        this.loaiDanhMuc = loaiDanhMuc;
    }

    @Basic
    @Column(name = "ten_loai_danh_muc", nullable = true, length = 100)
    public String getTenLoaiDanhMuc() {
        return tenLoaiDanhMuc;
    }

    public void setTenLoaiDanhMuc(String tenLoaiDanhMuc) {
        this.tenLoaiDanhMuc = tenLoaiDanhMuc;
    }

    @Basic
    @Column(name = "ma", nullable = true, length = 30)
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    @Basic
    @Column(name = "ten", nullable = true, length = 100)
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Basic
    @Column(name = "mo_ta", nullable = true, length = 100)
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
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
    @Column(name = "do_uu_tien", nullable = true)
    public Integer getDoUuTien() {
        return doUuTien;
    }

    public void setDoUuTien(Integer doUuTien) {
        this.doUuTien = doUuTien;
    }

    @Basic
    @Column(name = "nguoi_tao", nullable = true, length = 100)
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
    @Column(name = "nguoi_sua_cuoi", nullable = true, length = 100)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DmNgheNghiep that = (DmNgheNghiep) o;
        return id == that.id && Objects.equals(loaiDanhMuc, that.loaiDanhMuc) && Objects.equals(tenLoaiDanhMuc, that.tenLoaiDanhMuc) && Objects.equals(ma, that.ma) && Objects.equals(ten, that.ten) && Objects.equals(moTa, that.moTa) && Objects.equals(hieuLuc, that.hieuLuc) && Objects.equals(doUuTien, that.doUuTien) && Objects.equals(nguoiTao, that.nguoiTao) && Objects.equals(ngayTao, that.ngayTao) && Objects.equals(nguoiSuaCuoi, that.nguoiSuaCuoi) && Objects.equals(ngaySuaCuoi, that.ngaySuaCuoi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loaiDanhMuc, tenLoaiDanhMuc, ma, ten, moTa, hieuLuc, doUuTien, nguoiTao, ngayTao, nguoiSuaCuoi, ngaySuaCuoi);
    }
}
