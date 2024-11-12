package com.example.FQW.repositories;

import com.example.FQW.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByLoginAndPassword(String login, String password);

    Optional<Client> findClientByLogin(String login);

    Boolean existsClientByLogin(String login);

}
