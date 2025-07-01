package com.sea.desafio.converter;

import com.sea.desafio.dtos.endereco.request.EnderecoDTORequest;
import com.sea.desafio.dtos.endereco.response.EnderecoDTOResponse;
import com.sea.desafio.infrastructure.entities.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoConverter {

    public Endereco paraEnderecoEntity(EnderecoDTORequest enderecoDTORequest) {
        return Endereco.builder()
                .cep(enderecoDTORequest.getCep())
                .logradouro(enderecoDTORequest.getLogradouro())
                .bairro(enderecoDTORequest.getBairro())
                .cidade(enderecoDTORequest.getCidade())
                .uf(enderecoDTORequest.getUf())
                .complemento(enderecoDTORequest.getComplemento())
                .build();
    }

    public EnderecoDTOResponse paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTOResponse.builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .logradouro(endereco.getLogradouro())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .uf(endereco.getUf())
                .complemento(endereco.getComplemento())
                .build();
    }
}
