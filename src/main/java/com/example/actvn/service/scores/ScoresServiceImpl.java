package com.example.actvn.service.scores;


import com.example.actvn.entity.Scores;
import com.example.actvn.model.BaseModel;
import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.scores.ReportUserScoresResponse;
import com.example.actvn.model.scores.ScoresResponse;
import com.example.actvn.model.scores.UserScoresResponse;
import com.example.actvn.repository.classroom.ClassroomUserRepository;
import com.example.actvn.repository.scores.ScoresRepository;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.util.Constant;
import com.example.actvn.util.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScoresServiceImpl implements ScoresService {
    @Autowired
    ScoresRepository scoresRepository;
    @Autowired
    ClassroomUserRepository classroomUserRepository;

    @Override
    public ResponseModel getScores(Long classroomId, UserPrincipal userPrincipal) {
        ResponseModel responseModel = new ResponseModel();
        String message = "";
        try {
            List<UserScoresResponse> scores = scoresRepository.getScores(classroomId, null);
            message = " Get scores successfully!";
            responseModel.setData(scores);
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.OK);
            return responseModel;
        } catch (RuntimeException exception) {
            message = "Server error! Error: " + exception;
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setData(error);
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseModel;
        }
    }

    @Override
    public ResponseModel createOrUpdateScores(List<ScoresResponse> request, UserPrincipal userPrincipal) {
        ResponseModel responseModel = new ResponseModel();
        String message = "";
        HtmlUtil.validateRequest(request);
        try {
            if (Constant.ROLE.TEACHER != userPrincipal.getRoleId()) {
                message = "Not permission! ";
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                responseModel.setDescription(message);
                responseModel.setData(error);
                responseModel.setResponseStatus(HttpStatus.BAD_REQUEST);
                return responseModel;
            }
            if (request == null || request.size() == 0) {
                message = "Update scores student successfully!";
                BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
                responseModel.setDescription(message);
                responseModel.setData(success);
                responseModel.setResponseStatus(HttpStatus.OK);
                return responseModel;
            }
            List<Scores> scoresList = new ArrayList<>();
            for (ScoresResponse scoresResponse : request) {
                if (!checkTypeScores(scoresResponse.getType())) {
                    continue;
                }
                if (scoresResponse.getId() == null || scoresResponse.getId() == 0) {
                    boolean checkUserAndClassroom = classroomUserRepository.existsByClassroomIdAndUserId(scoresResponse.getClassroomId(), scoresResponse.getUserId());
                    if (checkUserAndClassroom) {
                        scoresList.add(newScores(scoresResponse));
                    }
                    continue;
                } else if (scoresResponse.getId() != null && scoresResponse.getId() != 0) {
                    Scores scores = updateScores(scoresResponse);
                    if (scores == null) {
                        continue;
                    }
                    scoresList.add(scores);
                }
            }
            scoresRepository.saveAll(scoresList);

            message = "Update score student successfully!";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            responseModel.setDescription(message);
            responseModel.setData(success);
            responseModel.setResponseStatus(HttpStatus.OK);
            return responseModel;

        } catch (RuntimeException exception) {
            message = "Server error! Error: " + exception;
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setDescription(message);
            responseModel.setData(error);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseModel;
        }
    }

    @Override
    public List<ReportUserScoresResponse> inKetQuaDanhGiaDiemQuaTrinh(Long classroomId, UserPrincipal userPrincipal) {
        List<ReportUserScoresResponse> scoresResponses = new ArrayList<>();
        try {
            List<UserScoresResponse> scores = scoresRepository.getScores(classroomId, null);
            for (UserScoresResponse response :
                    scores) {
                ReportUserScoresResponse scoresResponse = new ReportUserScoresResponse();
                scoresResponse.setHoVaTen(response.getNameUser());
                scoresResponse.setUserId(response.getUserId());
                for (ScoresResponse scoresResponse1 :
                        response.getScores()) {
                    if (scoresResponse1.getType() == Constant.SCORES.DIEM_CHUYEN_CAN)
                        scoresResponse.setDiemThanhPhanMot(scoresResponse1.getPoint().toString());
                    if (scoresResponse1.getType() == Constant.SCORES.DIEM_GIUA_KY)
                        scoresResponse.setDiemThanhPhanHai(scoresResponse1.getPoint().toString());
                }
                scoresResponses.add(scoresResponse);
            }
            return scoresResponses;
        } catch (RuntimeException exception) {

            return scoresResponses;
        }
    }

    private Scores updateScores(ScoresResponse scoresResponse) {
        Scores scores = scoresRepository.findById(scoresResponse.getId()).orElse(null);
        if (scores == null) {
            return null;
        }
        if (scoresResponse.getUserId() != scores.getUserId()
                || scoresResponse.getClassroomId() != scores.getClassroomId()
                || scoresResponse.getType() != scores.getType()) {
            return null;
        }
        scores.setPoint(scoresResponse.getPoint());
        Date now = new Date();
        Timestamp updateNow = new Timestamp(now.getTime());
        scores.setUpdatedAt(updateNow);
        return scores;
    }

    private Scores newScores(ScoresResponse scoresResponse) {
        Scores scores = new Scores();
        scores.setUserId(scoresResponse.getUserId());
        scores.setClassroomId(scoresResponse.getClassroomId());
        scores.setType(scoresResponse.getType());
        scores.setPoint(scoresResponse.getPoint());
        return scores;
    }

    private boolean checkTypeScores(long type) {
        switch (Integer.parseInt("" + type)) {
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }
}
