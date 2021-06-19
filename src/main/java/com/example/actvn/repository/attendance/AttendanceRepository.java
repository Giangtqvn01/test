package com.example.actvn.repository.attendance;

import com.example.actvn.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>,AttendanceRepositoryCustom {
    @Query(value = "select count(*) from attendance " +
            "where created_at between STR_TO_DATE(:toDate,'%Y-%m-%d %H:%i:%s')" +
            " and STR_TO_DATE(:fromDate,'%Y-%m-%d %H:%i:%s')" +
            " and imei=:imei" ,nativeQuery = true)
    Long exitsByImeiInAttendance(String toDate , String fromDate ,String imei);
}
