package com.example.actvn.model.scores;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserScoresResponse {
    private Long userId;
    private Long classroomId;
    private String nameUser;
    private String nameClassroom;
    private String codeUser;
    private List<ScoresResponse> scores;
}
