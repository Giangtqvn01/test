package com.example.actvn.repository.account;
import com.example.actvn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {
    Optional<User> findByUserName(String userName);

    Optional<User> findByIdAndActive(long id, long active);

//    @Query("SELECT acc.ma  FROM  User acc  WHERE acc.roleCd=:roleCd")
//    List<String> getListMaAccountFollowRole(@Param("roleCd") String roleCd);
}
