package com.headout.globetrotter.repository;

import com.headout.globetrotter.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {

    @Query("SELECT p FROM Place p WHERE p.id <> :correctPlaceId ORDER BY RANDOM() LIMIT 3")
    List<Place> findRandomWrongAnswers(@Param("correctPlaceId") Integer correctPlaceId);
}
