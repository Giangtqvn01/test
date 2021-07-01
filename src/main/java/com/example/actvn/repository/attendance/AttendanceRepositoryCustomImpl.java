package com.example.actvn.repository.attendance;

import com.example.actvn.model.attendance.AttendanceResponse;
import com.example.actvn.model.attendance.HistoryAttendanceResponse;
import com.example.actvn.model.attendance.HistoryAttendanceScheduleResponse;
import com.example.actvn.util.DataUtil;
import com.example.actvn.util.Logit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttendanceRepositoryCustomImpl implements AttendanceRepositoryCustom {
    private final Logit log = Logit.getInstance(AttendanceRepositoryCustomImpl.class);
    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;


    @Override
    public List<AttendanceResponse> getAttendanceFlowUserId(Long userId) {
        List<AttendanceResponse> responses = new ArrayList<>();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            HashMap map = new HashMap();
            strQueryGetAttendanceFlowUserId(map, stringBuilder, userId);
            Query query = entityManager.createNativeQuery(stringBuilder.toString());
            map.forEach((k, v) -> query.setParameter(k.toString(), v));
            List<Object[]> resultList = query.getResultList();
            if (resultList != null) {
                resultList.forEach(object -> {
                    AttendanceResponse response = new AttendanceResponse();
                    response.setClassroomId(DataUtil.safeToLong(object[0]));
                    response.setClassroomName(DataUtil.safeToString(object[1]));
                    response.setSumAttendance(DataUtil.safeToLong(object[2]));
                    response.setUserAttendance(DataUtil.safeToLong(object[3]));
                    responses.add(response);
                });
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return responses;
    }

    @Override
    public List<HistoryAttendanceScheduleResponse> getHistoryAttendanceSchedule(Long classroomId, Long userId) {
        List<HistoryAttendanceScheduleResponse> responses = new ArrayList<>();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            HashMap map = new HashMap();
            strQueryGetHistoryAttendanceSchedule(map, stringBuilder, userId, classroomId);
            Query query = entityManager.createNativeQuery(stringBuilder.toString());
            map.forEach((k, v) -> query.setParameter(k.toString(), v));
            List<Object[]> resultList = query.getResultList();
            if (resultList != null) {
                List<HistoryAttendanceResponse> historyAttendances = new ArrayList<>();
                for (int i = 0; i < resultList.size(); i++) {

                        Object[] o1 = resultList.get(i);
                        Object[] o2 = resultList.get(i+1==resultList.size()?i:i+1);
                        HistoryAttendanceResponse historyAttendanceResponse = new HistoryAttendanceResponse();
                        historyAttendanceResponse.setId(DataUtil.safeToLong(o1[0]));
                        historyAttendanceResponse.setUserId(DataUtil.safeToLong(o1[1]));
                        historyAttendanceResponse.setPresent(DataUtil.safeToString(o1[3]));
                        historyAttendanceResponse.setNote(DataUtil.safeToString(o1[4]));
                        historyAttendanceResponse.setCreateDate(DataUtil.safeToString(o1[5]));
                        historyAttendances.add(historyAttendanceResponse);
                        if (!DataUtil.safeToLong(o1[1]).equals(DataUtil.safeToLong(o2[1])) || i+1==resultList.size()) {
                            HistoryAttendanceScheduleResponse historyAttendanceScheduleResponse = new HistoryAttendanceScheduleResponse();
                            historyAttendanceScheduleResponse.setUserId(DataUtil.safeToLong(o1[1]));
                            historyAttendanceScheduleResponse.setScheduleId(DataUtil.safeToLong(o1[2]));
                            historyAttendanceScheduleResponse.setUserName(DataUtil.safeToString(o1[6]));
                            historyAttendanceScheduleResponse.setClassroomName(DataUtil.safeToString(o1[7]));
                            historyAttendanceScheduleResponse.setClassroomId(DataUtil.safeToLong(o1[8]));
                            historyAttendanceScheduleResponse.setHistoryAttendances(historyAttendances);
                            responses.add(historyAttendanceScheduleResponse);
                            historyAttendances = new ArrayList<>();
                        }


                }
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return responses;
    }

    private void strQueryGetHistoryAttendanceSchedule(HashMap map, StringBuilder stringBuilder, Long userId, Long classroomId) {
        stringBuilder.append(" SELECT att.id,\n" +
                "       att.user_id,\n" +
                "       att.schedule_id,\n" +
                "       att.present,\n" +
                "       att.note,\n" +
                "       DATE_FORMAT(att.created_at, '%d-%m-%Y') ,\n" +
                "       us.name,\n" +
                "       sch.classroom_name,\n" +
                "       sch.classroom_id\n" +
                "FROM kma_database.user us\n" +
                "JOIN kma_database.attendance att ON (us.id = att.user_id)\n" +
                "JOIN kma_database.schedule sch ON (sch.id = att.schedule_id)\n" +
                "WHERE sch.classroom_id =:classroomId\n");
        if (userId != null && userId != 0) {
            stringBuilder.append(" and att.user_id =:userId \n");
            map.put("userId", userId);
        }
        stringBuilder.append("ORDER BY us.id ");
        map.put("classroomId", classroomId);
    }

    private void strQueryGetAttendanceFlowUserId(HashMap map, StringBuilder stringBuilder, Long userId) {
        stringBuilder.append("select  sch.classroom_id,sch.classroom_name,\n" +
                "count((select count(tong.id) from (select * from attendance where schedule_id=att.schedule_id group by DATE_FORMAT(created_at, \"%d-%m-%Y\")) tong)) sum_attendance\n" +
                ",count((SELECT count(id) FROM kma_database.attendance where user_id = att.user_id and schedule_id =att.schedule_id)) user_attendance\n" +
                " from attendance att \n" +
                " join schedule sch on(sch.id = att.schedule_id) " +
                "  where att.user_id =:userId\n" +
                " group by att.user_id , sch.classroom_id");
        map.put("userId", userId);
    }
}
