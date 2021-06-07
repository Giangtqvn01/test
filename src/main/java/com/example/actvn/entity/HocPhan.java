package com.example.actvn.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Data
@Entity
@Table(name = "hoc_phan")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HocPhan {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "ma", nullable = true, length = 20)
    private String ma;
    @Basic
    @Column(name = "ten", nullable = true, length = 250)
    private String ten;
    @Basic
    @Column(name = "so_tin", nullable = true)
    private Integer soTin;
    @Basic
    @Column(name = "chuyen_nganh", nullable = true, length = 250)
    private String chuyenNganh;
    @Basic
    @Column(name = "hoc_ki", nullable = true, length = 20)
    private String hocKi;
    @Basic
    @Column(name = "giang_vien_ID", nullable = true,insertable = false, updatable = false)
    private Integer giangVienId;
    @Basic
    @Column(name = "si_so", nullable = true)
    private Integer siSo;
    @Basic
    @Column(name = "ghi_chu", nullable = true, length = 250)
    private String ghiChu;
    @Basic
    @Column(name = "nguoi_tao", nullable = true, length = 250)
    private String nguoiTao;
    @Basic
    @Column(name = "ngay_tao", nullable = true)
    private Timestamp ngayTao;
    @Basic
    @Column(name = "nguoi_sua_cuoi", nullable = true, length = 250)
    private String nguoiSuaCuoi;
    @Basic
    @Column(name = "ngay_sua_cuoi", nullable = true)
    private Timestamp ngaySuaCuoi;
    @Basic
    @Column(name = "hieu_luc", nullable = true, length = 1)
    private String hieuLuc;

    @ManyToOne
    @JoinColumn(name = "giang_vien_ID") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Account giangVien;
    @OneToMany(mappedBy = "monHocId", cascade = CascadeType.ALL)
    // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<ThoiGianHocPhan> thoiGianHocPhans;
}
