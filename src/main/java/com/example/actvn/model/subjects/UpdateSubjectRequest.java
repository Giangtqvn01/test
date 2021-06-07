package com.example.actvn.model.subjects;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UpdateSubjectRequest {
    @NotNull
    private Integer id;
    @NotBlank
    private String ma;
    @NotBlank
    private String ten;
    @NotBlank
    private String chuyenNganh;
    private String hocKi;
    @NotNull
    private Integer soTin;
    @NotBlank
    private Integer giangVienId;
    private Integer siSo;
    @Valid
    List<UpdateSubjectsTimeRequest> subjectsTimeRequests;
}
