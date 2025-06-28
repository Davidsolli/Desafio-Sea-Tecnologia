package com.sea.desafio.dtos.telefone.request;

import com.sea.desafio.enums.TipoTelefone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTORequest {

    @Pattern(regexp = "\\d{10,11}", message = "O número deve conter entre 10 e 11 dígitos numéricos")
    @NotBlank(message = "Você deve preencher o campo número")
    private String numero;
    private TipoTelefone tipoTelefone;
}
