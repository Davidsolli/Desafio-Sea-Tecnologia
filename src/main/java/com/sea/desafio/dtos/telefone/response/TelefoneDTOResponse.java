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

    public String getNumero() {
        if (tipoTelefone == TipoTelefone.CELULAR) {
            return numero.replaceAll("(\\d{2})(\\d{1})(\\d{4})(\\d{4})", "($1) $2$3-$4");
        } else {
            return numero.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
        }
    }
}
