package com.example.actvn.repository.classroom;

import com.example.actvn.entity.ClassroomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomUserRepository extends JpaRepository<ClassroomUser, Long> {
    boolean existsByClassroomIdAndUserId(Long classroomId, Long userId);
}
