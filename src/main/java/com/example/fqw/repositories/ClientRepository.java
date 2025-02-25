package com.example.fqw.repositories;

import com.example.fqw.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByLoginAndPassword(String login, String password);

    Optional<Client> findClientByLogin(String login);

    Optional<Client> findClientByChatId(String chatId);

    Boolean existsClientByLogin(String login);

}
