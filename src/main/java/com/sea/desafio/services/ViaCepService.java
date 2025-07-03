package com.sea.desafio.services;

import com.sea.desafio.dtos.ViaCepDTO;
import com.sea.desafio.infrastructure.clients.ViaCepClient;
import com.sea.desafio.infrastructure.exceptions.IllegalArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient viaCepClient;

    public ViaCepDTO encontrarEnderecoPorCep(String cep) {
        return viaCepClient.encontrarEnderecoPorCep(cepProcess(cep));
    }

    private String cepProcess(String cep) {

        String newCep = cep
                .replace(" ", "")
                .replace("-", "");

        if (!newCep.matches("\\d+") || !Objects.equals(newCep.length(), 8)) {
            throw new IllegalArgumentException("O cep contém caracteres inválidos, por favor verificar");
        }

        return newCep;
    }
}
