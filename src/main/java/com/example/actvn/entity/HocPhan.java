package com.example.actvn.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "hoc_phan", schema = "actvn", catalog = "")
public class HocPhan {
    private int id;
    private String ma;
    private String ten;
    private Integer soTin;
    private String chuyenNganh;
    private String hocKi;
    private Integer giangVienId;
    private Integer siSo;
    private String ghiChu;
    private String nguoiTao;
    private Timestamp ngayTao;
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
    @Column(name = "ma", nullable = true, length = 20)
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
    @Column(name = "so_tin", nullable = true)
    public Integer getSoTin() {
        return soTin;
    }

    public void setSoTin(Integer soTin) {
        this.soTin = soTin;
    }

    @Basic
    @Column(name = "chuyen_nganh", nullable = true, length = 250)
    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    @Basic
    @Column(name = "hoc_ki", nullable = true, length = 20)
    public String getHocKi() {
        return hocKi;
    }

    public void setHocKi(String hocKi) {
        this.hocKi = hocKi;
    }

    @Basic
    @Column(name = "giang_vien_ID", nullable = true)
    public Integer getGiangVienId() {
        return giangVienId;
    }

    public void setGiangVienId(Integer giangVienId) {
        this.giangVienId = giangVienId;
    }

    @Basic
    @Column(name = "si_so", nullable = true)
    public Integer getSiSo() {
        return siSo;
    }

    public void setSiSo(Integer siSo) {
        this.siSo = siSo;
    }

    @Basic
    @Column(name = "ghi_chu", nullable = true, length = 250)
    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
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
    @Column(name = "hieu_luc", nullable = true, length = 1)
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
        HocPhan hocPhan = (HocPhan) o;
        return id == hocPhan.id && Objects.equals(ma, hocPhan.ma) && Objects.equals(ten, hocPhan.ten) && Objects.equals(soTin, hocPhan.soTin) && Objects.equals(chuyenNganh, hocPhan.chuyenNganh) && Objects.equals(hocKi, hocPhan.hocKi) && Objects.equals(giangVienId, hocPhan.giangVienId) && Objects.equals(siSo, hocPhan.siSo) && Objects.equals(ghiChu, hocPhan.ghiChu) && Objects.equals(nguoiTao, hocPhan.nguoiTao) && Objects.equals(ngayTao, hocPhan.ngayTao) && Objects.equals(nguoiSuaCuoi, hocPhan.nguoiSuaCuoi) && Objects.equals(ngaySuaCuoi, hocPhan.ngaySuaCuoi) && Objects.equals(hieuLuc, hocPhan.hieuLuc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ma, ten, soTin, chuyenNganh, hocKi, giangVienId, siSo, ghiChu, nguoiTao, ngayTao, nguoiSuaCuoi, ngaySuaCuoi, hieuLuc);
    }
}
