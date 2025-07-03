package com.sea.desafio.services;

import com.sea.desafio.converter.EmailConverter;
import com.sea.desafio.dtos.email.request.EmailDTORequest;
import com.sea.desafio.dtos.email.response.EmailDTOResponse;
import com.sea.desafio.infrastructure.entities.Cliente;
import com.sea.desafio.infrastructure.entities.Email;
import com.sea.desafio.infrastructure.exceptions.ResourceNotFoundException;
import com.sea.desafio.infrastructure.repositories.ClienteRepository;
import com.sea.desafio.infrastructure.repositories.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailConverter emailConverter;
    private final ClienteRepository clienteRepository;
    private final EmailRepository emailRepository;

    public List<EmailDTOResponse> salvarNovosEmails(List<EmailDTORequest> emailDTORequestList, Long clienteId) {

        Cliente cliente = clienteRepository
                .findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        List<Email> novosEmails = emailConverter.paraEmailLista(emailDTORequestList);

        novosEmails.forEach(email -> email.setCliente(cliente));

        cliente.getEmails().addAll(novosEmails);

        Cliente clienteAtualizado = clienteRepository.save(cliente);

        return clienteAtualizado.getEmails().stream().map(emailConverter::paraEmailDTO).collect(Collectors.toList());
    }

    public EmailDTOResponse encontrarEmailPorId(Long emailId) {

        Email email = emailRepository
                .findById(emailId)
                .orElseThrow(() -> new ResourceNotFoundException("Email não encontrado"));

        return emailConverter.paraEmailDTO(email);
    }

    public void deletarEmailPorId(Long emailId) {
        Email email = emailRepository.findById(emailId)
                .orElseThrow(() -> new ResourceNotFoundException("Email não encontrado"));
        emailRepository.delete(email);
    }
}
