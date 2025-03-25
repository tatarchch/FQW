package com.example.fqw.repositories;

import com.example.fqw.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    /*Optional<Client> findClientByLogin(String login);

    Optional<Client> findClientByName(String name);*/

    Optional<Client> findClientByLogin(String login);

    Optional<Client> findClientByNameAndRole(String name, String role);

    Optional<Client> findClientByChatId(String chatId);

    Optional<Client> findClientByLoginAndRole(String login, String role);

    Optional<Client> findByIdAndRole(Long id, String role);

    List<Client> findAllByRole(String role);

    Boolean existsClientByLogin(String login);

}
