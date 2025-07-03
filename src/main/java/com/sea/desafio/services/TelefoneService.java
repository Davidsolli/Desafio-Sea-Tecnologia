package com.sea.desafio.services;

import com.sea.desafio.converter.TelefoneConverter;
import com.sea.desafio.dtos.telefone.request.TelefoneDTORequest;
import com.sea.desafio.dtos.telefone.response.TelefoneDTOResponse;
import com.sea.desafio.infrastructure.entities.Cliente;
import com.sea.desafio.infrastructure.entities.Telefone;
import com.sea.desafio.infrastructure.exceptions.ResourceNotFoundException;
import com.sea.desafio.infrastructure.repositories.ClienteRepository;
import com.sea.desafio.infrastructure.repositories.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TelefoneService {

    private final TelefoneConverter telefoneConverter;
    private final ClienteRepository clienteRepository;
    private final TelefoneRepository telefoneRepository;

    public List<TelefoneDTOResponse> salvarNovosTelefones(
            List<TelefoneDTORequest> telefoneDTORequestLista, Long clienteId
    ) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        List<Telefone> novosTelefones = telefoneConverter.paraTelefoneLista(telefoneDTORequestLista);

        novosTelefones.forEach(telefone -> telefone.setCliente(cliente));

        cliente.getTelefones().addAll(novosTelefones);

        Cliente clienteAtualizado = clienteRepository.save(cliente);

        return clienteAtualizado.getTelefones()
                .stream()
                .map(telefoneConverter::paraTelefoneDTO)
                .collect(Collectors.toList());
    }

    public TelefoneDTOResponse encontrarTelefonePorId(Long telefoneId) {
        Telefone telefone = telefoneRepository.findById(telefoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Telefone não encontrado"));

        return telefoneConverter.paraTelefoneDTO(telefone);
    }

    public void deletarTelefonePorId(Long telefoneId) {
        Telefone telefone = telefoneRepository.findById(telefoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Telefone não encontrado"));

        telefoneRepository.delete(telefone);
    }
}
