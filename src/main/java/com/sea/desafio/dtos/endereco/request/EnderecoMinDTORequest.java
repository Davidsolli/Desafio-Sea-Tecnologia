package com.sea.desafio.dtos.endereco.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoMinDTORequest {

    private String logradouro;
    private String bairro;
    private String cidade;
    @Size(min = 2, max = 2, message = "Uf deve ter exatamente 2 caracteres")
    private String uf;
    private String complemento;
}
