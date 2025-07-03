package com.sea.desafio.infrastructure.repositories;

import com.sea.desafio.infrastructure.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
