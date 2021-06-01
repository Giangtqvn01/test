package com.example.actvn.service.Class;

import com.example.actvn.entity.DsLop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListClassRepository extends JpaRepository<DsLop,Long> {
    List<DsLop> findByLopId(Integer lopId);
}
