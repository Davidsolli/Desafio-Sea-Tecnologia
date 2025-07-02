package com.sea.desafio.converter;

import com.sea.desafio.dtos.ViaCepDTO;
import com.sea.desafio.dtos.endereco.request.EnderecoDTORequest;
import com.sea.desafio.dtos.endereco.request.EnderecoMinDTORequest;
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

    public Endereco atualizarEndereco(Endereco endereco, EnderecoMinDTORequest enderecoMinDTORequest) {
        return Endereco.builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .logradouro(
                        enderecoMinDTORequest.getLogradouro() != null ? enderecoMinDTORequest.getLogradouro() : endereco.getLogradouro()
                )
                .bairro(enderecoMinDTORequest.getBairro() != null ? enderecoMinDTORequest.getBairro() : endereco.getBairro())
                .cidade(enderecoMinDTORequest.getCidade() != null ? enderecoMinDTORequest.getCidade() : endereco.getCidade())
                .uf(enderecoMinDTORequest.getUf() != null ? enderecoMinDTORequest.getUf() : endereco.getUf())
                .complemento(
                        enderecoMinDTORequest.getComplemento() != null ? enderecoMinDTORequest.getComplemento() : endereco.getComplemento()
                )
                .cliente(endereco.getCliente())
                .build();
    }

    public Endereco atualizarEnderecoPorCep(Endereco endereco, ViaCepDTO dados) {
        return Endereco.builder()
                .id(endereco.getId())
                .cep(dados.getCep())
                .logradouro(dados.getLogradouro())
                .bairro(dados.getBairro())
                .cidade(dados.getLocalidade())
                .uf(dados.getUf())
                .complemento(endereco.getComplemento())
                .cliente(endereco.getCliente())
                .build();
    }
}
