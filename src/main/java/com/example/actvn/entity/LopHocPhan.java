package com.example.actvn.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "lop_hoc_phan", schema = "actvn", catalog = "")
public class LopHocPhan {
    private int id;
    private Integer giaoVienId;
    private Integer sinhVienId;
    private Integer monHocId;
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
    @Column(name = "giao_vien_ID", nullable = true)
    public Integer getGiaoVienId() {
        return giaoVienId;
    }

    public void setGiaoVienId(Integer giaoVienId) {
        this.giaoVienId = giaoVienId;
    }

    @Basic
    @Column(name = "sinh_vien_ID", nullable = true)
    public Integer getSinhVienId() {
        return sinhVienId;
    }

    public void setSinhVienId(Integer sinhVienId) {
        this.sinhVienId = sinhVienId;
    }

    @Basic
    @Column(name = "mon_hoc_ID", nullable = true)
    public Integer getMonHocId() {
        return monHocId;
    }

    public void setMonHocId(Integer monHocId) {
        this.monHocId = monHocId;
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
        LopHocPhan that = (LopHocPhan) o;
        return id == that.id && Objects.equals(giaoVienId, that.giaoVienId) && Objects.equals(sinhVienId, that.sinhVienId) && Objects.equals(monHocId, that.monHocId) && Objects.equals(ghiChu, that.ghiChu) && Objects.equals(nguoiTao, that.nguoiTao) && Objects.equals(ngayTao, that.ngayTao) && Objects.equals(nguoiSuaCuoi, that.nguoiSuaCuoi) && Objects.equals(ngaySuaCuoi, that.ngaySuaCuoi) && Objects.equals(hieuLuc, that.hieuLuc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, giaoVienId, sinhVienId, monHocId, ghiChu, nguoiTao, ngayTao, nguoiSuaCuoi, ngaySuaCuoi, hieuLuc);
    }
}
