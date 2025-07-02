package com.sea.desafio.services;

import com.sea.desafio.converter.EnderecoConverter;
import com.sea.desafio.dtos.endereco.request.EnderecoMinDTORequest;
import com.sea.desafio.dtos.endereco.response.EnderecoDTOResponse;
import com.sea.desafio.infrastructure.entities.Endereco;
import com.sea.desafio.infrastructure.exceptions.ResourceNotFoundException;
import com.sea.desafio.infrastructure.repositories.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoConverter enderecoConverter;

    public EnderecoDTOResponse atualizarEndereco(Long enderecoId, EnderecoMinDTORequest enderecoMinDTORequest) {
        Endereco endereco = enderecoRepository
                .findById(enderecoId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado"));
        Endereco enderecoAtualizado = enderecoConverter.atualizarEndereco(endereco, enderecoMinDTORequest);
        return enderecoConverter.paraEnderecoDTO(enderecoRepository.save(enderecoAtualizado));
    }
}
