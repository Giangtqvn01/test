package com.example.actvn.repository.scores;

import com.example.actvn.model.scores.UserScoresResponse;

import java.util.List;

public interface ScoresRepositoryCustom {
    List<UserScoresResponse> getScores(Long classroomId, Long userId);
}
