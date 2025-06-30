package com.sea.desafio.converter;

import com.sea.desafio.dtos.cliente.request.ClienteDTORequest;
import com.sea.desafio.dtos.cliente.response.ClienteDTOResponse;
import com.sea.desafio.dtos.email.request.EmailDTORequest;
import com.sea.desafio.dtos.email.response.EmailDTOResponse;
import com.sea.desafio.dtos.endereco.request.EnderecoDTORequest;
import com.sea.desafio.dtos.endereco.response.EnderecoDTOResponse;
import com.sea.desafio.dtos.telefone.request.TelefoneDTORequest;
import com.sea.desafio.dtos.telefone.response.TelefoneDTOResponse;
import com.sea.desafio.entities.Cliente;
import com.sea.desafio.entities.Email;
import com.sea.desafio.entities.Endereco;
import com.sea.desafio.entities.Telefone;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteConverter {

    /* Para entity */

    public Cliente paraClienteEntity(ClienteDTORequest clienteDTORequest) {
        Cliente cliente = Cliente.builder()
                .nome(clienteDTORequest.getNome())
                .cpf(clienteDTORequest.getCpf())
                .emails(
                        clienteDTORequest.getListaEmail() != null ? paraEmailLista(clienteDTORequest.getListaEmail()) : null
                )
                .telefones(
                        clienteDTORequest.getListaTelefone() != null ? paraTelefoneLista(clienteDTORequest.getListaTelefone()) : null
                )
                .build();

        if (clienteDTORequest.getEndereco() != null) {
            Endereco endereco = paraEnderecoEntity(clienteDTORequest.getEndereco());
            endereco.setCliente(cliente);
            cliente.setEndereco(endereco);
        }

        return cliente;
    }

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

    public Email paraEmailEntity(EmailDTORequest emailDTORequest) {
        return Email.builder()
                .email(emailDTORequest.getEmail())
                .build();
    }

    public Telefone paraTelefoneEntity(TelefoneDTORequest telefoneDTORequest) {
        return Telefone.builder()
                .numero(telefoneDTORequest.getNumero())
                .tipoTelefone(telefoneDTORequest.getTipoTelefone())
                .build();
    }

    public List<Email> paraEmailLista(List<EmailDTORequest> emailDTOList) {
        return emailDTOList.stream().map(this::paraEmailEntity).collect(Collectors.toList());
    }

    public List<Telefone> paraTelefoneLista(List<TelefoneDTORequest> telefoneDTOList) {
        return telefoneDTOList.stream().map(this::paraTelefoneEntity).collect(Collectors.toList());
    }

    /* Para DTO Response */

    public ClienteDTOResponse paraClienteDTO(Cliente cliente) {
        return ClienteDTOResponse.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .endereco(paraEnderecoDTO(cliente.getEndereco()))
                .listaEmail(cliente.getEmails() != null ? paraEmailDTOLista(cliente.getEmails()) : null)
                .listaTelefone(cliente.getTelefones() != null ? paraTelefoneDTOLista(cliente.getTelefones()) : null)
                .build();
    }

    public EnderecoDTOResponse paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTOResponse.builder()
                .cep(endereco.getCep())
                .logradouro(endereco.getLogradouro())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .uf(endereco.getUf())
                .complemento(endereco.getComplemento())
                .build();
    }

    public EmailDTOResponse paraEmailDTO(Email email) {
        return EmailDTOResponse.builder()
                .email(email.getEmail())
                .build();
    }

    public TelefoneDTOResponse paraTelefoneDTO(Telefone telefone) {
        return TelefoneDTOResponse.builder()
                .numero(telefone.getNumero())
                .tipoTelefone(telefone.getTipoTelefone())
                .build();
    }

    public List<EmailDTOResponse> paraEmailDTOLista(List<Email> emailsEntityLista) {
        return emailsEntityLista.stream().map(this::paraEmailDTO).collect(Collectors.toList());
    }

    public List<TelefoneDTOResponse> paraTelefoneDTOLista(List<Telefone> telefoneEntityLista) {
        return telefoneEntityLista.stream().map(this::paraTelefoneDTO).collect(Collectors.toList());
    }

    public List<ClienteDTOResponse> paraClienteDTOResponseLista(List<Cliente> clienteEntityLista) {
        return clienteEntityLista.stream().map(this::paraClienteDTO).collect(Collectors.toList());
    }
}