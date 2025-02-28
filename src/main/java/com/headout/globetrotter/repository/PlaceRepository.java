package com.headout.globetrotter.repository;

import com.headout.globetrotter.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {

}
