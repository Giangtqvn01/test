package com.example.actvn.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "account_detail", schema = "actvn", catalog = "")
public class AccountDetail {
    private int id;
    private Integer accountId;
    private String cmtnd;
    private String noiCap;
    private Timestamp ngayCapCmtnd;
    private String diaChi;
    private String queQuanChiTiet;
    private String hocKyNhapHoc;
    private int thuongTruTinhId;
    private int thuongTruHuyenId;
    private int thuongTruXaId;
    private int danTocId;
    private int gioiTinhId;
    private int quocGiaId;
    private Integer tonGiaoId;
    private String khoa;
    private String chuyenNganh;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "CMTND", nullable = true, length = 13)
    public String getCmtnd() {
        return cmtnd;
    }

    public void setCmtnd(String cmtnd) {
        this.cmtnd = cmtnd;
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
    @Column(name = "ngay_cap_cmtnd", nullable = true)
    public Timestamp getNgayCapCmtnd() {
        return ngayCapCmtnd;
    }

    public void setNgayCapCmtnd(Timestamp ngayCapCmtnd) {
        this.ngayCapCmtnd = ngayCapCmtnd;
    }

    @Basic
    @Column(name = "dia_chi", nullable = true, length = 250)
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Basic
    @Column(name = "que_quan_chi_tiet", nullable = true, length = 10)
    public String getQueQuanChiTiet() {
        return queQuanChiTiet;
    }

    public void setQueQuanChiTiet(String queQuanChiTiet) {
        this.queQuanChiTiet = queQuanChiTiet;
    }

    @Basic
    @Column(name = "hoc_ky_nhap_hoc", nullable = true, length = 10)
    public String getHocKyNhapHoc() {
        return hocKyNhapHoc;
    }

    public void setHocKyNhapHoc(String hocKyNhapHoc) {
        this.hocKyNhapHoc = hocKyNhapHoc;
    }

    @Basic
    @Column(name = "thuong_tru_tinh_ID", nullable = false)
    public int getThuongTruTinhId() {
        return thuongTruTinhId;
    }

    public void setThuongTruTinhId(int thuongTruTinhId) {
        this.thuongTruTinhId = thuongTruTinhId;
    }

    @Basic
    @Column(name = "thuong_tru_huyen_ID", nullable = false)
    public int getThuongTruHuyenId() {
        return thuongTruHuyenId;
    }

    public void setThuongTruHuyenId(int thuongTruHuyenId) {
        this.thuongTruHuyenId = thuongTruHuyenId;
    }

    @Basic
    @Column(name = "thuong_tru_xa_ID", nullable = false)
    public int getThuongTruXaId() {
        return thuongTruXaId;
    }

    public void setThuongTruXaId(int thuongTruXaId) {
        this.thuongTruXaId = thuongTruXaId;
    }

    @Basic
    @Column(name = "dan_toc_ID", nullable = false)
    public int getDanTocId() {
        return danTocId;
    }

    public void setDanTocId(int danTocId) {
        this.danTocId = danTocId;
    }

    @Basic
    @Column(name = "gioi_tinh_ID", nullable = false)
    public int getGioiTinhId() {
        return gioiTinhId;
    }

    public void setGioiTinhId(int gioiTinhId) {
        this.gioiTinhId = gioiTinhId;
    }

    @Basic
    @Column(name = "quoc_gia_ID", nullable = false)
    public int getQuocGiaId() {
        return quocGiaId;
    }

    public void setQuocGiaId(int quocGiaId) {
        this.quocGiaId = quocGiaId;
    }

    @Basic
    @Column(name = "ton_giao_id", nullable = true)
    public Integer getTonGiaoId() {
        return tonGiaoId;
    }

    public void setTonGiaoId(Integer tonGiaoId) {
        this.tonGiaoId = tonGiaoId;
    }

    @Basic
    @Column(name = "khoa", nullable = true, length = 10)
    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    @Basic
    @Column(name = "chuyen_nganh", nullable = true, length = 250)
    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDetail that = (AccountDetail) o;
        return id == that.id && thuongTruTinhId == that.thuongTruTinhId && thuongTruHuyenId == that.thuongTruHuyenId && thuongTruXaId == that.thuongTruXaId && danTocId == that.danTocId && gioiTinhId == that.gioiTinhId && quocGiaId == that.quocGiaId && Objects.equals(accountId, that.accountId) && Objects.equals(cmtnd, that.cmtnd) && Objects.equals(noiCap, that.noiCap) && Objects.equals(ngayCapCmtnd, that.ngayCapCmtnd) && Objects.equals(diaChi, that.diaChi) && Objects.equals(queQuanChiTiet, that.queQuanChiTiet) && Objects.equals(hocKyNhapHoc, that.hocKyNhapHoc) && Objects.equals(tonGiaoId, that.tonGiaoId) && Objects.equals(khoa, that.khoa) && Objects.equals(chuyenNganh, that.chuyenNganh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, cmtnd, noiCap, ngayCapCmtnd, diaChi, queQuanChiTiet, hocKyNhapHoc, thuongTruTinhId, thuongTruHuyenId, thuongTruXaId, danTocId, gioiTinhId, quocGiaId, tonGiaoId, khoa, chuyenNganh);
    }
}
