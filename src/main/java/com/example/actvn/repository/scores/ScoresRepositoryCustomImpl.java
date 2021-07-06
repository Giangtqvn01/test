package com.example.actvn.repository.scores;

import com.example.actvn.model.scores.ScoresResponse;
import com.example.actvn.model.scores.UserScoresResponse;
import com.example.actvn.repository.attendance.AttendanceRepositoryCustomImpl;
import com.example.actvn.util.DataUtil;
import com.example.actvn.util.Logit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoresRepositoryCustomImpl implements ScoresRepositoryCustom {
    private final Logit log = Logit.getInstance(AttendanceRepositoryCustomImpl.class);
    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;

    @Override
    public List<UserScoresResponse> getScores(Long classroomId, Long userId) {
        List<UserScoresResponse> responses = new ArrayList<>();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            HashMap map = new HashMap();
            sqlQueryGetScoresFollowClassroom(map, stringBuilder, classroomId, userId);
            Query query = entityManager.createNativeQuery(stringBuilder.toString());
            map.forEach((k, v) -> query.setParameter(k.toString(), v));
            List<Object[]> resultList = query.getResultList();
            if (resultList != null) {
                List<ScoresResponse> scoresResponses = new ArrayList<>();
                for (int i = 0; i < resultList.size(); i++) {
                    Object[] o1 = resultList.get(i);
                    Object[] o2 = resultList.get(i + 1 == resultList.size() ? i : i + 1);
                    ScoresResponse scoresResponse = new ScoresResponse();
                    scoresResponse.setUserId(DataUtil.safeToLong(o1[0]));
                    scoresResponse.setClassroomId(DataUtil.safeToLong(o1[3]));
                    scoresResponse.setId(DataUtil.safeToLong(o1[5]));
                    scoresResponse.setType(DataUtil.safeToLong(o1[6]));
                    scoresResponse.setPoint(DataUtil.safeToDouble(o1[7]));
                    if (scoresResponse.getId() !=0){
                        scoresResponses.add(scoresResponse);
                    }
                    if (!DataUtil.safeToLong(o1[0]).equals(DataUtil.safeToLong(o2[0])) || i + 1 == resultList.size()) {
                        UserScoresResponse response = new UserScoresResponse();
                        response.setUserId(DataUtil.safeToLong(o1[0]));
                        response.setCodeUser(DataUtil.safeToString(o1[1]));
                        response.setNameUser(DataUtil.safeToString(o1[2]));
                        response.setClassroomId(DataUtil.safeToLong(o1[3]));
                        response.setNameClassroom(DataUtil.safeToString(o1[4]));
                        response.setScores(scoresResponses);
                        responses.add(response);
                        scoresResponses = new ArrayList<>();
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return responses;
    }

    private void sqlQueryGetScoresFollowClassroom(HashMap map, StringBuilder sql, Long classroomId, Long userId) {
        sql.append(" SELECT u.id userId\n" +
                "      , u.username ,\n" +
                "       u.name user_name,\n" +
                "       clu.classroom_id classroomId,\n" +
                "       cla.name classroomName,\n" +
                "       scr.id scoresId,\n" +
                "       scr.type scoresType,\n" +
                "       scr.point scoresPoint\n" +
                "FROM kma_database.user u\n" +
                "JOIN classroom_user clu ON (u.id = clu.user_id\n" +
                "                            AND u.is_active =1)\n" +
                "JOIN classroom cla ON (cla.id =clu.classroom_id\n" +
                "                       AND cla.is_active =1)\n" +
                "LEFT JOIN scores scr ON (cla.id = scr.classroom_id AND scr.user_id =u.id)\n" +
                "WHERE u.role_id =2\n" +
                "  AND clu.classroom_id =:classroomId ");
        map.put("classroomId", classroomId);
        if (userId != null && userId != 0) {
            sql.append(" and u.id=:userId ");
            map.put("userId", userId);
        }
        sql.append(" order by u.id ");
    }
}
