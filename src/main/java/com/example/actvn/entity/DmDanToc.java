package com.example.actvn.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "dm_dan_toc", schema = "actvn", catalog = "")
public class DmDanToc {
    private int id;
    private String ma;
    private String ten;
    private String moTa;
    private String hieuLuc;
    private Integer doUuTien;
    private String giaTri;
    private String nguoiTao;
    private String nguoiSuaCuoi;
    private Timestamp ngayTao;
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
    @Column(name = "Ma", nullable = true, length = 20)
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    @Basic
    @Column(name = "Ten", nullable = true, length = 250)
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Basic
    @Column(name = "mo_ta", nullable = true, length = 250)
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Basic
    @Column(name = "hieu_luc", nullable = false, length = 250)
    public String getHieuLuc() {
        return hieuLuc;
    }

    public void setHieuLuc(String hieuLuc) {
        this.hieuLuc = hieuLuc;
    }

    @Basic
    @Column(name = "Do_uu_tien", nullable = true)
    public Integer getDoUuTien() {
        return doUuTien;
    }

    public void setDoUuTien(Integer doUuTien) {
        this.doUuTien = doUuTien;
    }

    @Basic
    @Column(name = "gia_tri", nullable = true, length = 50)
    public String getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(String giaTri) {
        this.giaTri = giaTri;
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
    @Column(name = "nguoi_sua_cuoi", nullable = true, length = 100)
    public String getNguoiSuaCuoi() {
        return nguoiSuaCuoi;
    }

    public void setNguoiSuaCuoi(String nguoiSuaCuoi) {
        this.nguoiSuaCuoi = nguoiSuaCuoi;
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
        DmDanToc dmDanToc = (DmDanToc) o;
        return id == dmDanToc.id && Objects.equals(ma, dmDanToc.ma) && Objects.equals(ten, dmDanToc.ten) && Objects.equals(moTa, dmDanToc.moTa) && Objects.equals(hieuLuc, dmDanToc.hieuLuc) && Objects.equals(doUuTien, dmDanToc.doUuTien) && Objects.equals(giaTri, dmDanToc.giaTri) && Objects.equals(nguoiTao, dmDanToc.nguoiTao) && Objects.equals(nguoiSuaCuoi, dmDanToc.nguoiSuaCuoi) && Objects.equals(ngayTao, dmDanToc.ngayTao) && Objects.equals(ngaySuaCuoi, dmDanToc.ngaySuaCuoi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ma, ten, moTa, hieuLuc, doUuTien, giaTri, nguoiTao, nguoiSuaCuoi, ngayTao, ngaySuaCuoi);
    }
}