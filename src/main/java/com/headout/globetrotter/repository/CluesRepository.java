package com.headout.globetrotter.repository;

import com.headout.globetrotter.entity.Clues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CluesRepository extends JpaRepository<Clues, Integer> {

}
