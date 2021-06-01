package com.example.actvn.repository.listClass;

import com.example.actvn.entity.DsLop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DsLopRepository extends JpaRepository<DsLop, Integer> {
    @Query("SELECT COUNT(dsLop.sinhVienId) FROM DsLop dsLop WHERE dsLop.lopId =:idLop")
    Long getCountStudentInClass(Integer idLop);
}
