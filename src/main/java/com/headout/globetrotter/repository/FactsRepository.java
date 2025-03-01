package com.headout.globetrotter.repository;

import com.headout.globetrotter.entity.Facts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactsRepository extends JpaRepository<Facts, Integer> {
}
