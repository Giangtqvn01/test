package com.example.actvn.model.classroom;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UpdateClassRequest {
    @NotNull
    private Long id;
    @NotEmpty(message = "Name class not null")
    private String name;
    @NotNull(message = "subject not null")
    private Long subjectId;
    private Long active;
}
