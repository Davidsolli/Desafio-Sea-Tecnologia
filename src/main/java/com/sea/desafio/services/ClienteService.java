package com.sea.desafio.services;

import com.sea.desafio.converter.ClienteConverter;
import com.sea.desafio.dtos.cliente.request.ClienteDTORequest;
import com.sea.desafio.dtos.cliente.request.ClienteMinDTORequest;
import com.sea.desafio.dtos.cliente.response.ClienteDTOResponse;
import com.sea.desafio.entities.Cliente;
import com.sea.desafio.exceptions.ResourceNotFoundException;
import com.sea.desafio.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteConverter clienteConverter;

    public ClienteDTOResponse salvarNovoCliente(ClienteDTORequest clienteDTORequest) {
        Cliente cliente = clienteConverter.paraClienteEntity(clienteDTORequest);
        return clienteConverter.paraClienteDTO(clienteRepository.save(cliente));
    }

    public List<ClienteDTOResponse> listaClientes() {
        return clienteConverter.paraClienteDTOResponseLista(clienteRepository.listaCliente());
    }

    public ClienteDTOResponse atualizarDadosDeCliente(ClienteMinDTORequest clienteMinDTORequest, Long clienteId) {
        Cliente cliente = clienteRepository
                .findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        Cliente clienteAtualizado = clienteConverter.atualizarDadosDeClientes(cliente, clienteMinDTORequest);
        return clienteConverter.paraClienteDTO(clienteRepository.save(clienteAtualizado));
    }

    public void excluirCliente(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

    public ClienteDTOResponse encontrarClientePorId(Long clienteId) {
        return clienteConverter.paraClienteDTO(
                clienteRepository
                        .findById(clienteId)
                        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"))
        );
    }
}
