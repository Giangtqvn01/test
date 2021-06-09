package com.example.actvn.model.subjects;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubjectRequest {
    private String ma;
    private String ten;
    private String chuyenNganh;
    private String hocKi;
    private Integer soTin;
    private Integer giangVienId;
    private String giangVien;
}
