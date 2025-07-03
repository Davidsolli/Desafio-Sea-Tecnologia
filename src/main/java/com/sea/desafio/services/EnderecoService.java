package com.sea.desafio.services;

import com.sea.desafio.converter.EnderecoConverter;
import com.sea.desafio.dtos.ViaCepDTO;
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
    private final ViaCepService viaCepService;

    public EnderecoDTOResponse atualizarEndereco(Long enderecoId, EnderecoMinDTORequest enderecoMinDTORequest) {
        Endereco endereco = enderecoRepository
                .findById(enderecoId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado"));
        Endereco enderecoAtualizado = enderecoConverter.atualizarEndereco(endereco, enderecoMinDTORequest);
        return enderecoConverter.paraEnderecoDTO(enderecoRepository.save(enderecoAtualizado));
    }

    public EnderecoDTOResponse atualizarEnderecoPorCep(Long enderecoId, String cep) {
        Endereco endereco = enderecoRepository
                .findById(enderecoId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado"));
        ViaCepDTO dados = viaCepService.encontrarEnderecoPorCep(cep);
        Endereco enderecoAtualizado = enderecoConverter.atualizarEnderecoPorCep(endereco, dados);
        return enderecoConverter.paraEnderecoDTO(enderecoRepository.save(enderecoAtualizado));
    }

    public EnderecoDTOResponse encontrarEnderecoPorId(Long enderecoId) {
        Endereco endereco = enderecoRepository
                .findById(enderecoId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado"));
        return enderecoConverter.paraEnderecoDTO(endereco);
    }
}
