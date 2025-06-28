package com.sea.desafio.dtos.telefone.response;

import com.sea.desafio.enums.TipoTelefone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTOResponse {

    private String numero;
    private TipoTelefone tipoTelefone;
}
