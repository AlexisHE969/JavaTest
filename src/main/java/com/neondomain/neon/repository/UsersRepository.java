package com.neondomain.neon.repository;

import com.neondomain.neon.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUser(String email);
    boolean existsByUser(String user);
}
