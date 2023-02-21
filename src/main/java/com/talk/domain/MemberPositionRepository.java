package com.talk.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberPositionRepository extends JpaRepository<MemberPosition, Long> {

    List<MemberPosition> findByPositionId(Long positionId);

    Optional<MemberPosition> findByMemberId(Long memberId);

    boolean existsByMemberIdAndPositionId(Long memberId, Long positionId);

}
