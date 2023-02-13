package com.talk.domain;

import com.talk.domain.enumpack.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

//    boolean existsById(String id);

    boolean existsByIdAndAuthProvider(String id, AuthProvider authProvider);
}
