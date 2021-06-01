package com.example.actvn.repository.account;

import com.example.actvn.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor {
    Optional<Account> findByTaiKhoanAndHieuLuc(String taiKhoan, String hieuLuc);

    Optional<Account> findByIdAndHieuLuc(Integer id, String hieuLuc);

    @Query("SELECT acc.ma  FROM  Account acc  WHERE acc.roleCd=:roleCd")
    List<String> getListMaAccountFollowRole(@Param("roleCd") String roleCd);
}
