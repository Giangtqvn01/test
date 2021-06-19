package com.example.actvn.model.classroom;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

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
