package com.talk.login.repository;

import com.talk.entity.Users;
import com.talk.enums.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

//    boolean existsById(String id);

    boolean existsByIdAndAuthProvider(String id, AuthProvider authProvider);
}
