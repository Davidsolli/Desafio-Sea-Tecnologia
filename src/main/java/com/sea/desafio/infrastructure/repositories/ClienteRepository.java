package com.sea.desafio.infrastructure.repositories;

import com.sea.desafio.infrastructure.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //Alterar para Set para mais de 2 tabelas
    @Query("SELECT obj FROM Cliente obj JOIN FETCH obj.telefones JOIN FETCH obj.endereco")
    List<Cliente> listaCliente();
}
