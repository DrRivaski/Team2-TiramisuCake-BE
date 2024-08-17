package com.softeer.backend.fo_domain.draw.repository;

import com.softeer.backend.fo_domain.draw.domain.DrawParticipationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DrawParticipationInfoRepository extends JpaRepository<DrawParticipationInfo, Integer> {
    Optional<DrawParticipationInfo> findDrawParticipationInfoByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE DrawParticipationInfo d SET d.drawWinningCount = d.drawWinningCount + 1 WHERE d.userId = :userId")
    void increaseWinCount(Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE DrawParticipationInfo d SET d.drawLosingCount = d.drawLosingCount + 1 WHERE d.userId = :userId")
    void increaseLoseCount(Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE DrawParticipationInfo d SET d.drawParticipationCount = d.drawParticipationCount + 1 WHERE d.userId = :userId")
    void increaseParticipationCount(Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE DrawParticipationInfo d SET d.drawParticipationCount = 1 WHERE d.userId = :userId")
    void setParticipationCountToOne(Integer userId);
}
