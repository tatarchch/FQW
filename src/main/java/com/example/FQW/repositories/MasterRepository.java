package com.example.FQW.repositories;

import com.example.FQW.entity.Master;
import com.example.FQW.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {

    List<Master> findAllByPlace(Place place);

    List<Master> findAllByLevelGreaterThanEqual(Integer level);

    List<Master> findAllByIdIn(List<Long> mastersId);

    Optional <Master> findMasterByName(String name);



}
