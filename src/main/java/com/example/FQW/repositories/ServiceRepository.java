package com.example.FQW.repositories;

import com.example.FQW.dto.PlaceDto;
import com.example.FQW.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {

    /*default List<PlaceDto> getPlaceDtos(){
        return
    };*/
}
