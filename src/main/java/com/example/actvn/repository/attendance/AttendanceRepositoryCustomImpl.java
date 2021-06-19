package com.example.actvn.repository.attendance;

import com.example.actvn.model.attendance.AttendanceResponse;
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
            strQueryGetAttendanceFlowUserId(map,stringBuilder,userId);
            Query query = entityManager.createNativeQuery(stringBuilder.toString());
            map.forEach((k, v) -> query.setParameter(k.toString(),v));
            List<Object[]> resultList = query.getResultList();
            if (resultList!=null){
                resultList.forEach(object -> {
                    AttendanceResponse response = new AttendanceResponse();
                    response.setClassroomId(DataUtil.safeToLong(object[0]));
                    response.setClassroomName(DataUtil.safeToString(object[1]));
                    response.setSumAttendance(DataUtil.safeToLong(object[2]));
                    response.setUserAttendance(DataUtil.safeToLong(object[3]));
                    responses.add(response);
                });
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return responses;
    }

    private void strQueryGetAttendanceFlowUserId(HashMap map, StringBuilder stringBuilder, Long userId) {
    stringBuilder.append("select  sch.classroom_id,sch.classroom_name,\n" +
            "(select count(tong.id) from (select * from attendance where schedule_id=att.schedule_id group by DATE_FORMAT(created_at, \"%d-%m-%Y\")) tong) sum_attendance\n" +
            ",(SELECT count(id) FROM kma_database.attendance where user_id = att.user_id and schedule_id =att.schedule_id) user_attendance\n" +
            " from attendance att \n" +
            " join schedule sch on(sch.id = att.schedule_id) " +
            "  where att.user_id =:userId\n" +
            " group by att.user_id , att.schedule_id");
        map.put("userId", userId);
    }
}
