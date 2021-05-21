package com.example.actvn.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "nguoi_than", schema = "actvn", catalog = "")
public class NguoiThan {
    private int id;
    private String hoVaTen;
    private int quanHeId;
    private int ngheNghiep;
    private Timestamp namSinh;
    private String cmtnd;
    private Timestamp ngayCap;
    private String noiCap;
    private String nguoiTao;
    private Timestamp ngayTao;
    private String nguoiSuaCuoi;
    private Timestamp ngaySuaCuoi;
    private Integer danTocId;
    private Integer accountId;
    private Integer quocGiaId;
    private Integer thuongTruHuyenId;
    private Integer thuongTruXaId;
    private Integer thuongTruId;
    private Integer gioiTinhId;
    private Integer tonGiaoId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ho_va_ten", nullable = true, length = 10)
    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    @Basic
    @Column(name = "quan_he_ID", nullable = false)
    public int getQuanHeId() {
        return quanHeId;
    }

    public void setQuanHeId(int quanHeId) {
        this.quanHeId = quanHeId;
    }

    @Basic
    @Column(name = "nghe_nghiep", nullable = false)
    public int getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(int ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    @Basic
    @Column(name = "nam_sinh", nullable = true)
    public Timestamp getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Timestamp namSinh) {
        this.namSinh = namSinh;
    }

    @Basic
    @Column(name = "CMTND", nullable = true, length = 20)
    public String getCmtnd() {
        return cmtnd;
    }

    public void setCmtnd(String cmtnd) {
        this.cmtnd = cmtnd;
    }

    @Basic
    @Column(name = "ngay_cap", nullable = true)
    public Timestamp getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(Timestamp ngayCap) {
        this.ngayCap = ngayCap;
    }

    @Basic
    @Column(name = "noi_cap", nullable = true, length = 250)
    public String getNoiCap() {
        return noiCap;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
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
    @Column(name = "dan_toc_ID", nullable = true)
    public Integer getDanTocId() {
        return danTocId;
    }

    public void setDanTocId(Integer danTocId) {
        this.danTocId = danTocId;
    }

    @Basic
    @Column(name = "account_ID", nullable = true)
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "quoc_gia_ID", nullable = true)
    public Integer getQuocGiaId() {
        return quocGiaId;
    }

    public void setQuocGiaId(Integer quocGiaId) {
        this.quocGiaId = quocGiaId;
    }

    @Basic
    @Column(name = "thuong_tru_huyen_ID", nullable = true)
    public Integer getThuongTruHuyenId() {
        return thuongTruHuyenId;
    }

    public void setThuongTruHuyenId(Integer thuongTruHuyenId) {
        this.thuongTruHuyenId = thuongTruHuyenId;
    }

    @Basic
    @Column(name = "thuong_tru_xa_ID", nullable = true)
    public Integer getThuongTruXaId() {
        return thuongTruXaId;
    }

    public void setThuongTruXaId(Integer thuongTruXaId) {
        this.thuongTruXaId = thuongTruXaId;
    }

    @Basic
    @Column(name = "thuong_tru_ID", nullable = true)
    public Integer getThuongTruId() {
        return thuongTruId;
    }

    public void setThuongTruId(Integer thuongTruId) {
        this.thuongTruId = thuongTruId;
    }

    @Basic
    @Column(name = "gioi_tinh_ID", nullable = true)
    public Integer getGioiTinhId() {
        return gioiTinhId;
    }

    public void setGioiTinhId(Integer gioiTinhId) {
        this.gioiTinhId = gioiTinhId;
    }

    @Basic
    @Column(name = "ton_giao_ID", nullable = true)
    public Integer getTonGiaoId() {
        return tonGiaoId;
    }

    public void setTonGiaoId(Integer tonGiaoId) {
        this.tonGiaoId = tonGiaoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NguoiThan nguoiThan = (NguoiThan) o;
        return id == nguoiThan.id && quanHeId == nguoiThan.quanHeId && ngheNghiep == nguoiThan.ngheNghiep && Objects.equals(hoVaTen, nguoiThan.hoVaTen) && Objects.equals(namSinh, nguoiThan.namSinh) && Objects.equals(cmtnd, nguoiThan.cmtnd) && Objects.equals(ngayCap, nguoiThan.ngayCap) && Objects.equals(noiCap, nguoiThan.noiCap) && Objects.equals(nguoiTao, nguoiThan.nguoiTao) && Objects.equals(ngayTao, nguoiThan.ngayTao) && Objects.equals(nguoiSuaCuoi, nguoiThan.nguoiSuaCuoi) && Objects.equals(ngaySuaCuoi, nguoiThan.ngaySuaCuoi) && Objects.equals(danTocId, nguoiThan.danTocId) && Objects.equals(accountId, nguoiThan.accountId) && Objects.equals(quocGiaId, nguoiThan.quocGiaId) && Objects.equals(thuongTruHuyenId, nguoiThan.thuongTruHuyenId) && Objects.equals(thuongTruXaId, nguoiThan.thuongTruXaId) && Objects.equals(thuongTruId, nguoiThan.thuongTruId) && Objects.equals(gioiTinhId, nguoiThan.gioiTinhId) && Objects.equals(tonGiaoId, nguoiThan.tonGiaoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hoVaTen, quanHeId, ngheNghiep, namSinh, cmtnd, ngayCap, noiCap, nguoiTao, ngayTao, nguoiSuaCuoi, ngaySuaCuoi, danTocId, accountId, quocGiaId, thuongTruHuyenId, thuongTruXaId, thuongTruId, gioiTinhId, tonGiaoId);
    }
}
