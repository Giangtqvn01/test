package com.example.actvn.repository.scores;

import com.example.actvn.entity.Scores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoresRepository extends JpaRepository<Scores, Long>,ScoresRepositoryCustom {
}
