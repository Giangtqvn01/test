package com.example.actvn.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "diem_danh", schema = "actvn", catalog = "")
public class DiemDanh {
    private int id;
    private Integer lopHocPhanId;
    private Integer sinhVienId;
    private String taoDo;
    private String nguoiTao;
    private Timestamp ngayTao;
    private Timestamp ngaySuaCuoi;
    private String nguoiSuaCuoi;
    private Integer lopHocPhan;
    private String hieuLuc;
    private Integer qrcodeId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "lop_hoc_phan_ID", nullable = true)
    public Integer getLopHocPhanId() {
        return lopHocPhanId;
    }

    public void setLopHocPhanId(Integer lopHocPhanId) {
        this.lopHocPhanId = lopHocPhanId;
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

    @Basic
    @Column(name = "qrcode_ID", nullable = true)
    public Integer getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(Integer qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiemDanh diemDanh = (DiemDanh) o;
        return id == diemDanh.id && Objects.equals(lopHocPhanId, diemDanh.lopHocPhanId) && Objects.equals(sinhVienId, diemDanh.sinhVienId) && Objects.equals(taoDo, diemDanh.taoDo) && Objects.equals(nguoiTao, diemDanh.nguoiTao) && Objects.equals(ngayTao, diemDanh.ngayTao) && Objects.equals(ngaySuaCuoi, diemDanh.ngaySuaCuoi) && Objects.equals(nguoiSuaCuoi, diemDanh.nguoiSuaCuoi) && Objects.equals(lopHocPhan, diemDanh.lopHocPhan) && Objects.equals(hieuLuc, diemDanh.hieuLuc) && Objects.equals(qrcodeId, diemDanh.qrcodeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lopHocPhanId, sinhVienId, taoDo, nguoiTao, ngayTao, ngaySuaCuoi, nguoiSuaCuoi, lopHocPhan, hieuLuc, qrcodeId);
    }
}
