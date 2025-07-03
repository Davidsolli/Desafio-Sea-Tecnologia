package com.sea.desafio.infrastructure.repositories;

import com.sea.desafio.infrastructure.entities.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
