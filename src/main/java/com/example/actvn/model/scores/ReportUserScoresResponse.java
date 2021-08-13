package com.example.actvn.model.scores;

import lombok.Data;

@Data
public class ReportUserScoresResponse {
    private Long userId;
    private String maSinhVien;
    private String hoVaTen;
    private String lop;
    private String diemThanhPhanMot;
    private String diemThanhPhanHai;
    private String diemQuaTrinhBangSo;
    private String diemQuaTrinhBangChu;
    private String ghiChu;
}
