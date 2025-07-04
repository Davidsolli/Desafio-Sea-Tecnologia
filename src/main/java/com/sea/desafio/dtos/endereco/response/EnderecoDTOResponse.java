package com.sea.desafio.dtos.endereco.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTOResponse {

    private Long id;
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;

    public String getCep() {
        return cep.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
    }
}
