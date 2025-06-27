package com.sea.desafio.dtos.telefone.request;

import com.sea.desafio.enums.TipoTelefone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDTORequest {

    private String numero;
    private TipoTelefone telefone;
}
