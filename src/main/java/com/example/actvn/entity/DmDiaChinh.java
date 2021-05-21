package com.example.actvn.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "dm_dia_chinh", schema = "actvn", catalog = "")
public class DmDiaChinh {
    private int id;
    private String ma;
    private String ten;
    private String tenNganNgon;
    private String tenKd;
    private String tenDayDu;
    private String capDiaChinh;
    private String moTa;
    private String maTraCuu;
    private String hieuLuc;
    private String nguoiTao;
    private Timestamp ngayTao;
    private Timestamp ngaySuaCuoi;
    private String nguoiSuaCuoi;
    private Integer diaChinhChaId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Ma", nullable = true, length = 10)
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    @Basic
    @Column(name = "ten", nullable = true, length = 250)
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Basic
    @Column(name = "ten_ngan_ngon", nullable = true, length = 250)
    public String getTenNganNgon() {
        return tenNganNgon;
    }

    public void setTenNganNgon(String tenNganNgon) {
        this.tenNganNgon = tenNganNgon;
    }

    @Basic
    @Column(name = "ten_kd", nullable = true, length = 250)
    public String getTenKd() {
        return tenKd;
    }

    public void setTenKd(String tenKd) {
        this.tenKd = tenKd;
    }

    @Basic
    @Column(name = "ten_day_du", nullable = true, length = 250)
    public String getTenDayDu() {
        return tenDayDu;
    }

    public void setTenDayDu(String tenDayDu) {
        this.tenDayDu = tenDayDu;
    }

    @Basic
    @Column(name = "cap_dia_chinh", nullable = true, length = 250)
    public String getCapDiaChinh() {
        return capDiaChinh;
    }

    public void setCapDiaChinh(String capDiaChinh) {
        this.capDiaChinh = capDiaChinh;
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
    @Column(name = "ma_tra_cuu", nullable = true, length = 250)
    public String getMaTraCuu() {
        return maTraCuu;
    }

    public void setMaTraCuu(String maTraCuu) {
        this.maTraCuu = maTraCuu;
    }

    @Basic
    @Column(name = "hieu_luc", nullable = false, length = 1)
    public String getHieuLuc() {
        return hieuLuc;
    }

    public void setHieuLuc(String hieuLuc) {
        this.hieuLuc = hieuLuc;
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
    @Column(name = "ngay_sua_cuoi", nullable = true)
    public Timestamp getNgaySuaCuoi() {
        return ngaySuaCuoi;
    }

    public void setNgaySuaCuoi(Timestamp ngaySuaCuoi) {
        this.ngaySuaCuoi = ngaySuaCuoi;
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
    @Column(name = "dia_chinh_cha_ID", nullable = true)
    public Integer getDiaChinhChaId() {
        return diaChinhChaId;
    }

    public void setDiaChinhChaId(Integer diaChinhChaId) {
        this.diaChinhChaId = diaChinhChaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DmDiaChinh that = (DmDiaChinh) o;
        return id == that.id && Objects.equals(ma, that.ma) && Objects.equals(ten, that.ten) && Objects.equals(tenNganNgon, that.tenNganNgon) && Objects.equals(tenKd, that.tenKd) && Objects.equals(tenDayDu, that.tenDayDu) && Objects.equals(capDiaChinh, that.capDiaChinh) && Objects.equals(moTa, that.moTa) && Objects.equals(maTraCuu, that.maTraCuu) && Objects.equals(hieuLuc, that.hieuLuc) && Objects.equals(nguoiTao, that.nguoiTao) && Objects.equals(ngayTao, that.ngayTao) && Objects.equals(ngaySuaCuoi, that.ngaySuaCuoi) && Objects.equals(nguoiSuaCuoi, that.nguoiSuaCuoi) && Objects.equals(diaChinhChaId, that.diaChinhChaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ma, ten, tenNganNgon, tenKd, tenDayDu, capDiaChinh, moTa, maTraCuu, hieuLuc, nguoiTao, ngayTao, ngaySuaCuoi, nguoiSuaCuoi, diaChinhChaId);
    }
}
