package com.headout.globetrotter.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.headout.globetrotter.entity.Facts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FactsRepository extends JpaRepository<Facts, Integer> {
    List<Facts> findByPlaceId(Integer placeId);
}
