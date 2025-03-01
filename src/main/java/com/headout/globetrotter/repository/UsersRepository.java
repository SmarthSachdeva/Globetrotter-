package com.headout.globetrotter.repository;

import com.headout.globetrotter.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    List<Users> findTopByOrderByScoreDesc(Pageable pageable);

    Users findByEmail(String email);

    Optional<Users> findByUsername(String username);
//
//    boolean existsByUsername(String username);
//
//    boolean existsByEmail(String email);
}
