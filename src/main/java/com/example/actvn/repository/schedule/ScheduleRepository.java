package com.example.actvn.repository.schedule;

import com.example.actvn.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>, JpaSpecificationExecutor<Schedule> {
    @Query(value = "SELECT sch.* FROM kma_database.schedule sch\n" +
            "join classroom_user cls on (cls.classroom_id = sch.classroom_id)\n" +
            "where cls.user_id=:userId and( sch.date between :dateFrom and :dateTo ) " +
            "and sch.month between :monthFrom and :monthTo \n" + " and sch.year between :yearFrom and :yearTo " +
            "and sch.classroom_id=:classroomId " +
            "order by sch.datetime", nativeQuery = true)
    List<Schedule> getScheduleByDateTimeAndClassroomId(@Param("dateFrom") Integer dateFrom, @Param("dateTo") Integer dateTo,
                                                       @Param("monthFrom") Integer monthFrom, @Param("monthTo") Integer monthTo,
                                                       @Param("yearFrom") Integer yearFrom, @Param("yearTo") Integer yearTo,
                                                       @Param("userId") Long userId, @Param("classroomId") Long classroomId);

    @Query(value = "SELECT sch.* FROM kma_database.schedule sch\n" +
            "join classroom_user cls on (cls.classroom_id = sch.classroom_id)\n" +
            "where cls.user_id=:userId and( sch.date between :dateFrom and :dateTo ) " +
            "and sch.month between :monthFrom and :monthTo \n" + " and sch.year between :yearFrom and :yearTo " +
            "order by sch.datetime", nativeQuery = true)
    List<Schedule> getScheduleByDateTime(@Param("dateFrom") Integer dateFrom, @Param("dateTo") Integer dateTo,
                                         @Param("monthFrom") Integer monthFrom, @Param("monthTo") Integer monthTo,
                                         @Param("yearFrom") Integer yearFrom, @Param("yearTo") Integer yearTo,
                                         @Param("userId") Long userId);

}
