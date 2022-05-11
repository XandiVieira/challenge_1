package com.cadastrocliente.repository;

import com.cadastrocliente.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByCpf(String cpf);

    Page<Client> findAll(Pageable pageable);

    boolean existsByCpf(String cpf);
}