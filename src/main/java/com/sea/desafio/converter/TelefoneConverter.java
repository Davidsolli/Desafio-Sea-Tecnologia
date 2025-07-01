package com.sea.desafio.converter;

import com.sea.desafio.dtos.telefone.request.TelefoneDTORequest;
import com.sea.desafio.dtos.telefone.response.TelefoneDTOResponse;
import com.sea.desafio.infrastructure.entities.Telefone;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TelefoneConverter {

    public Telefone paraTelefoneEntity(TelefoneDTORequest telefoneDTORequest) {
        return Telefone.builder()
                .numero(telefoneDTORequest.getNumero())
                .tipoTelefone(telefoneDTORequest.getTipoTelefone())
                .build();
    }

    public TelefoneDTOResponse paraTelefoneDTO(Telefone telefone) {
        return TelefoneDTOResponse.builder()
                .id(telefone.getId())
                .numero(telefone.getNumero())
                .tipoTelefone(telefone.getTipoTelefone())
                .build();
    }

    public List<Telefone> paraTelefoneLista(List<TelefoneDTORequest> telefoneDTOList) {
        return telefoneDTOList.stream().map(this::paraTelefoneEntity).collect(Collectors.toList());
    }

    public List<TelefoneDTOResponse> paraTelefoneDTOLista(List<Telefone> telefoneEntityLista) {
        return telefoneEntityLista.stream().map(this::paraTelefoneDTO).collect(Collectors.toList());
    }
}
