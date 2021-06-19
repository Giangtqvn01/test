package com.example.actvn.repository.classroom;

import com.example.actvn.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Classroom, Long>, JpaSpecificationExecutor<Classroom> {
}
