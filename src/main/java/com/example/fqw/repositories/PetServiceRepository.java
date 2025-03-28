package com.example.fqw.repositories;

import com.example.fqw.entity.PetService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetServiceRepository extends JpaRepository<PetService, Long> {

    List<PetService> findAllByLevelLessThanEqual(Integer level);

    Optional<PetService> findServiceByName(String name);
}
