package com.sea.desafio.infrastructure.repositories;

import com.sea.desafio.infrastructure.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
