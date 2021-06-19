package com.example.actvn.repository.schedule;

import com.example.actvn.entity.ClassroomUser;
import com.example.actvn.entity.Schedule;
import com.example.actvn.model.schedule.ScheduleRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleSpecification {
    public static Specification<Schedule> searchSchedule(ScheduleRequest request){
        return Specification.where(searchClassroomId(request.getClassroomId()));
    }
    private static Specification<Schedule> searchClassroomId(Long classroomId){
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (classroomId == null || classroomId ==0) return null;
            return criteriaBuilder.equal(root.get("classroom_id"), classroomId);
        };
    }
    private static Specification<Schedule> joinClassroom(Integer userId){
        return new Specification<Schedule>() {
            @Override
            public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (userId == null || userId == 0) return null;
                List<Predicate> predicates = new ArrayList<>();
                Join<Schedule, ClassroomUser> classroomUserJoin = root.join("classroom_id");
                predicates.add(criteriaBuilder.equal(classroomUserJoin.get("user_id"),userId));
                criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                return null;
            }
        };
    }
}
