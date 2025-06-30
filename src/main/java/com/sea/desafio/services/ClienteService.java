package com.sea.desafio.services;

import com.sea.desafio.converter.ClienteConverter;
import com.sea.desafio.dtos.cliente.request.ClienteDTORequest;
import com.sea.desafio.dtos.cliente.response.ClienteDTOResponse;
import com.sea.desafio.entities.Cliente;
import com.sea.desafio.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteConverter clienteConverter;

    public ClienteDTOResponse salvarNovoCliente(ClienteDTORequest clienteDTORequest) {
        Cliente cliente = clienteConverter.paraClienteEntity(clienteDTORequest);
        return clienteConverter.paraClienteDTO(clienteRepository.save(cliente));
    }
}
