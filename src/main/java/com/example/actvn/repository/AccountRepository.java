package com.example.actvn.repository;

import com.example.actvn.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByTaiKhoanAndHieuLuc(String taiKhoan, String hieuLuc);

    Optional<Account> findByIdAndHieuLuc(Integer id, String hieuLuc);
}
