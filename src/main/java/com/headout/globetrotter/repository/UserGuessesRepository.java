package com.headout.globetrotter.repository;


import com.headout.globetrotter.entity.UserGuesses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGuessesRepository extends JpaRepository<UserGuesses, Integer> {

}