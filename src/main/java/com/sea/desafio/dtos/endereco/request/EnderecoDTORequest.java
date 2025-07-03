package com.sea.desafio.dtos.endereco.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTORequest {

    @NotBlank(message = "Você deve preencher o campo cep")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 dígitos numéricos")
    private String cep;
    @NotBlank(message = "Você deve preencher o campo logradouro")
    private String logradouro;
    @NotBlank(message = "Você deve preencher o campo bairro")
    private String bairro;
    @NotBlank(message = "Você deve preencher o campo cidade")
    private String cidade;
    @NotBlank(message = "Você deve preencher o campo uf")
    @Size(min = 2, max = 2, message = "Uf deve ter exatamente 2 caracteres")
    private String uf;
    private String complemento;
}
