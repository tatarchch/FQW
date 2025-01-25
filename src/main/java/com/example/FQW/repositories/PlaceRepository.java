package com.example.FQW.repositories;

import com.example.FQW.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

   Optional <Place> findPlaceByName(String name);

}
