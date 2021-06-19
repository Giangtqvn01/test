package com.example.actvn.repository.subject;

import com.example.actvn.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor {

}
