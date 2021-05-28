package com.example.actvn.repository.classRepository;

import com.example.actvn.entity.Lop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Lop, Long>, JpaSpecificationExecutor {
    boolean existsByMa(String ma);

    @Query("SELECT COUNT(id) FROM Lop WHERE ma=:ma and id<>:id")
    Long findByMaAndId(String ma, Long id);

    @Query("SELECT COUNT(id) FROM Lop WHERE tenLop=:ten and id<>:id")
    Long findByTenAndId(String ten, Long id);
}
