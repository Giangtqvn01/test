package com.example.actvn.model.classModel;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UpdateClassRequest {
    private Long id;
    @NotEmpty
    @NotNull
    private String tenLop;
    @NotEmpty
    @NotNull
    private String ma;
    @NotNull
    private Integer chuyenNganhId;
    @NotNull
    private Integer khoaId;
    private String khoa;
    private String hieuLuc;
}
