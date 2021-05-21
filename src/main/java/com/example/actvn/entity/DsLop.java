package com.example.actvn.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ds_lop", schema = "actvn", catalog = "")
public class DsLop {
    private int id;
    private Timestamp thoiGianBatDau;
    private Timestamp thoiGianKetThuc;
    private Integer sinhVienId;
    private Integer giaoVienChuNhiemId;
    private Integer lopId;
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
    @Column(name = "thoi_gian_bat_dau", nullable = true)
    public Timestamp getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Timestamp thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    @Basic
    @Column(name = "thoi_gian_ket_thuc", nullable = true)
    public Timestamp getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Timestamp thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
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
    @Column(name = "giao_vien_chu_nhiem_ID", nullable = true)
    public Integer getGiaoVienChuNhiemId() {
        return giaoVienChuNhiemId;
    }

    public void setGiaoVienChuNhiemId(Integer giaoVienChuNhiemId) {
        this.giaoVienChuNhiemId = giaoVienChuNhiemId;
    }

    @Basic
    @Column(name = "lop_ID", nullable = true)
    public Integer getLopId() {
        return lopId;
    }

    public void setLopId(Integer lopId) {
        this.lopId = lopId;
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
        DsLop dsLop = (DsLop) o;
        return id == dsLop.id && Objects.equals(thoiGianBatDau, dsLop.thoiGianBatDau) && Objects.equals(thoiGianKetThuc, dsLop.thoiGianKetThuc) && Objects.equals(sinhVienId, dsLop.sinhVienId) && Objects.equals(giaoVienChuNhiemId, dsLop.giaoVienChuNhiemId) && Objects.equals(lopId, dsLop.lopId) && Objects.equals(hieuLuc, dsLop.hieuLuc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, thoiGianBatDau, thoiGianKetThuc, sinhVienId, giaoVienChuNhiemId, lopId, hieuLuc);
    }
}
