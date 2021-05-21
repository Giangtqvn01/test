package com.example.actvn.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "thoi_gian_hoc_phan", schema = "actvn", catalog = "")
public class ThoiGianHocPhan {
    private int id;
    private Timestamp ngayHoc;
    private String diaDiem;
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
    @Column(name = "ngay_hoc", nullable = true)
    public Timestamp getNgayHoc() {
        return ngayHoc;
    }

    public void setNgayHoc(Timestamp ngayHoc) {
        this.ngayHoc = ngayHoc;
    }

    @Basic
    @Column(name = "dia_diem", nullable = true, length = 10)
    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
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
        ThoiGianHocPhan that = (ThoiGianHocPhan) o;
        return id == that.id && Objects.equals(ngayHoc, that.ngayHoc) && Objects.equals(diaDiem, that.diaDiem) && Objects.equals(monHocId, that.monHocId) && Objects.equals(ghiChu, that.ghiChu) && Objects.equals(nguoiTao, that.nguoiTao) && Objects.equals(ngayTao, that.ngayTao) && Objects.equals(nguoiSuaCuoi, that.nguoiSuaCuoi) && Objects.equals(ngaySuaCuoi, that.ngaySuaCuoi) && Objects.equals(hieuLuc, that.hieuLuc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ngayHoc, diaDiem, monHocId, ghiChu, nguoiTao, ngayTao, nguoiSuaCuoi, ngaySuaCuoi, hieuLuc);
    }
}
