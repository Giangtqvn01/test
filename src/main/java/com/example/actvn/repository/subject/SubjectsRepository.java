package com.example.actvn.repository.subject;

import com.example.actvn.entity.HocPhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends JpaRepository<HocPhan, Integer>, JpaSpecificationExecutor {
    boolean existsByMa(String ma);

    @Query("SELECT COUNT(hp) FROM HocPhan hp WHERE hp.ma=:ma and hp.id <>:id")
    Long getExistByMaOtherId(@Param("Ma") String ma, @Param("id") Integer id);

}
