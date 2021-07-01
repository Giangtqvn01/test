package com.example.actvn.repository.schedule;

import com.example.actvn.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Query(value = "SELECT sch.* FROM kma_database.schedule sch\n" +
            "            where sch.classroom_id=:classroomId and(  sch.date between :dateFrom and :dateTo  )  \n" +
            "            and (sch.month between :monthFrom and :monthTo )  and (sch.year between :yearFrom and :yearTo)\n" +
            "            and (:time  between sch.start_time and sch.end_time)\n" +
            "            order by sch.datetime", nativeQuery = true)
    Optional<Schedule> getScheduleByClassroomId(@Param("dateFrom") Integer dateFrom, @Param("dateTo") Integer dateTo,
                                      @Param("monthFrom") Integer monthFrom, @Param("monthTo") Integer monthTo,
                                      @Param("yearFrom") Integer yearFrom, @Param("yearTo") Integer yearTo,
                                      @Param("classroomId") Long classroomId, @Param("time") String time);
    @Query(value = "select count(sch.id) from schedule sch \n" +
            "join classroom_user clu on (sch.classroom_id = clu.classroom_id)\n" +
            "where clu.user_id =:userId and sch.id = :scheduleId",nativeQuery = true)
    Long existsByUserIdInClassroom(@Param("userId") Long userId, @Param("scheduleId") Long scheduleId);

}
