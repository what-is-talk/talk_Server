package com.talk.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionPrivilegeRepository extends JpaRepository<PositionPrivilege, Long> {

    List<PositionPrivilege> findByPositionId(Long positionId);

    boolean existsByPositionAndPrivilege(Position position, Privilege privilege);

}
