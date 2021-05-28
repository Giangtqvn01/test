package com.example.actvn.model.classModel;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;

@Data
@JsonNaming()
public class ClassRequest {
    private String ma;
    private String tenLop;
    private String hieuLuc;
    private String khoa;
    private Integer khoaId;
    private Integer chuyenNganhId;
}
