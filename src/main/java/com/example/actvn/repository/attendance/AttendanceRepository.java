package com.example.actvn.repository.attendance;

import com.example.actvn.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>, AttendanceRepositoryCustom {
    @Query(value = "select count(*) from attendance " +
            "where created_at between STR_TO_DATE(:toDate,'%Y-%m-%d %H:%i:%s')" +
            " and STR_TO_DATE(:fromDate,'%Y-%m-%d %H:%i:%s')" +
            " and imei=:imei", nativeQuery = true)
    Long exitsByImeiInAttendance(String toDate, String fromDate, String imei);

    @Query(value = "SELECT att.*\n" +
            "FROM attendance att\n" +
            "JOIN classroom_user clu on(att.user_id = clu.user_id)\n" +
            "JOIN schedule sch ON (att.schedule_id = sch.id\n" +
            "                      AND clu.classroom_id =sch.classroom_id)\n" +
            "WHERE sch.classroom_id =:classroomId\n" +
            "GROUP BY DATE_FORMAT(att.created_at, '%d-%m-%Y')", nativeQuery = true)
    List<Attendance> getListAttendanceTime(@Param("classroomId") Long classroomId);
}
