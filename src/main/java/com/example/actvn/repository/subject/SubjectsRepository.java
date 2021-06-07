package com.example.actvn.repository.subject;

import com.example.actvn.entity.HocPhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends JpaRepository<HocPhan, Integer> {
    boolean existsByMa(String ma);

}
