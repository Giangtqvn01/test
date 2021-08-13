package com.example.actvn.model.scores;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming
public class ScoresResponse {
    private Long id;
    private Long userId;
    private Long classroomId;
    private Long type;
    private Double point;
}
