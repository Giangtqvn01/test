package com.example.actvn.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "qr_info", schema = "actvn", catalog = "")
public class QrInfo {
    private int id;
    private Timestamp thoiGianTao;
    private Timestamp thoiGianKetThuc;
    private String taoDo;
    private String nguoiTao;
    private Timestamp ngayTao;
    private Timestamp ngaySuaCuoi;
    private String nguoiSuaCuoi;
    private Integer lopHocPhan;
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
    @Column(name = "thoi_gian_tao", nullable = true)
    public Timestamp getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Timestamp thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
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
    @Column(name = "tao_do", nullable = true, length = 100)
    public String getTaoDo() {
        return taoDo;
    }

    public void setTaoDo(String taoDo) {
        this.taoDo = taoDo;
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
    @Column(name = "ngay_sua_cuoi", nullable = true)
    public Timestamp getNgaySuaCuoi() {
        return ngaySuaCuoi;
    }

    public void setNgaySuaCuoi(Timestamp ngaySuaCuoi) {
        this.ngaySuaCuoi = ngaySuaCuoi;
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
    @Column(name = "lop_hoc_phan", nullable = true)
    public Integer getLopHocPhan() {
        return lopHocPhan;
    }

    public void setLopHocPhan(Integer lopHocPhan) {
        this.lopHocPhan = lopHocPhan;
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
        QrInfo qrInfo = (QrInfo) o;
        return id == qrInfo.id && Objects.equals(thoiGianTao, qrInfo.thoiGianTao) && Objects.equals(thoiGianKetThuc, qrInfo.thoiGianKetThuc) && Objects.equals(taoDo, qrInfo.taoDo) && Objects.equals(nguoiTao, qrInfo.nguoiTao) && Objects.equals(ngayTao, qrInfo.ngayTao) && Objects.equals(ngaySuaCuoi, qrInfo.ngaySuaCuoi) && Objects.equals(nguoiSuaCuoi, qrInfo.nguoiSuaCuoi) && Objects.equals(lopHocPhan, qrInfo.lopHocPhan) && Objects.equals(hieuLuc, qrInfo.hieuLuc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, thoiGianTao, thoiGianKetThuc, taoDo, nguoiTao, ngayTao, ngaySuaCuoi, nguoiSuaCuoi, lopHocPhan, hieuLuc);
    }
}
