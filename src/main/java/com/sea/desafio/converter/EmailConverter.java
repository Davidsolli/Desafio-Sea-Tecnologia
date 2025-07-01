package com.sea.desafio.converter;

import com.sea.desafio.dtos.email.request.EmailDTORequest;
import com.sea.desafio.dtos.email.response.EmailDTOResponse;
import com.sea.desafio.infrastructure.entities.Email;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailConverter {

    public Email paraEmailEntity(EmailDTORequest emailDTORequest) {
        return Email.builder()
                .email(emailDTORequest.getEmail())
                .build();
    }

    public EmailDTOResponse paraEmailDTO(Email email) {
        return EmailDTOResponse.builder()
                .id(email.getId())
                .email(email.getEmail())
                .build();
    }

    public List<Email> paraEmailLista(List<EmailDTORequest> emailDTOList) {
        return emailDTOList.stream().map(this:: paraEmailEntity).collect(Collectors.toList());
    }

    public List<EmailDTOResponse> paraEmailDTOLista(List<Email> emailsEntityLista) {
        return emailsEntityLista.stream().map(this::paraEmailDTO).collect(Collectors.toList());
    }
}
