package com.sea.desafio.converter;

import com.sea.desafio.dtos.cliente.request.ClienteDTORequest;
import com.sea.desafio.dtos.cliente.request.ClienteMinDTORequest;
import com.sea.desafio.dtos.cliente.response.ClienteDTOResponse;
import com.sea.desafio.infrastructure.entities.Cliente;
import com.sea.desafio.infrastructure.entities.Endereco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClienteConverter {

    private final EnderecoConverter enderecoConverter;
    private final EmailConverter emailConverter;
    private final TelefoneConverter telefoneConverter;

    /* Para entity */

    public Cliente paraClienteEntity(ClienteDTORequest clienteDTORequest) {
        Cliente cliente = Cliente.builder()
                .nome(clienteDTORequest.getNome())
                .cpf(clienteDTORequest.getCpf())
                .emails(
                        clienteDTORequest.getListaEmail() != null ? emailConverter.paraEmailLista(
                                clienteDTORequest.getListaEmail()
                        ) : null
                )
                .telefones(
                        clienteDTORequest.getListaTelefone() != null ? telefoneConverter.paraTelefoneLista(
                                clienteDTORequest.getListaTelefone()
                        ) : null
                )
                .build();

        if (clienteDTORequest.getEndereco() != null) {
            Endereco endereco = enderecoConverter.paraEnderecoEntity(clienteDTORequest.getEndereco());
            endereco.setCliente(cliente);
            cliente.setEndereco(endereco);
        }

        return cliente;
    }

    /* Para DTO Response */

    public ClienteDTOResponse paraClienteDTO(Cliente cliente) {
        return ClienteDTOResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .endereco(enderecoConverter.paraEnderecoDTO(cliente.getEndereco()))
                .listaEmail(cliente.getEmails() != null ? emailConverter.paraEmailDTOLista(cliente.getEmails()) : null)
                .listaTelefone(cliente.getTelefones() != null ? telefoneConverter.paraTelefoneDTOLista(
                        cliente.getTelefones()
                ) : null)
                .build();
    }

    public List<ClienteDTOResponse> paraClienteDTOResponseLista(List<Cliente> clienteEntityLista) {
        return clienteEntityLista.stream().map(this::paraClienteDTO).collect(Collectors.toList());
    }

    // Atualizar dados de cliente

    public Cliente atualizarDadosDeClientes(Cliente cliente, ClienteMinDTORequest clienteMinDTORequest) {
        return Cliente.builder()
                .id(cliente.getId())
                .nome(clienteMinDTORequest.getNome() != null ? clienteMinDTORequest.getNome() : cliente.getNome())
                .cpf(clienteMinDTORequest.getCpf() != null ? clienteMinDTORequest.getCpf() : cliente.getCpf())
                .endereco(cliente.getEndereco())
                .emails(cliente.getEmails())
                .telefones(cliente.getTelefones())
                .build();
    }
}