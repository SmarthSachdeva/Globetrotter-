package com.headout.globetrotter.repository;

import com.headout.globetrotter.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query("SELECT u FROM User u ORDER BY u.score DESC")
    List<Users> findTopUsers(Pageable pageable);
}
