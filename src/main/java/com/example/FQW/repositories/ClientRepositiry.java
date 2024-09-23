package com.example.FQW.repositories;

import com.example.FQW.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepositiry extends JpaRepository<Client, Long> {
}
