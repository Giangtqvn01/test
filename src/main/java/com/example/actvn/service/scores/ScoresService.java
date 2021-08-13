package com.example.actvn.service.scores;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.scores.ReportUserScoresResponse;
import com.example.actvn.model.scores.ScoresResponse;
import com.example.actvn.security.UserPrincipal;

import java.util.List;

public interface ScoresService {
    ResponseModel getScores(Long classroomId, UserPrincipal userPrincipal);

    ResponseModel createOrUpdateScores(List<ScoresResponse> request, UserPrincipal userPrincipal);

    byte[] downloadFileScoresOfStudent(Long classroomId, UserPrincipal userPrincipal);

     List<ReportUserScoresResponse> getListReportUserScoresResponse(Long classroomId, UserPrincipal userPrincipal);
}
