package com.sea.desafio.dtos.cliente.request;

import com.sea.desafio.dtos.email.request.EmailDTORequest;
import com.sea.desafio.dtos.endereco.request.EnderecoDTORequest;
import com.sea.desafio.dtos.telefone.request.TelefoneDTORequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTORequest {

    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @NotBlank(message = "Você deve preencher o campo nome")
    private String nome;
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos")
    @NotBlank(message = "Você deve preencher o campo cpf")
    private String cpf;
    @Valid
    private EnderecoDTORequest endereco;
    @NotEmpty(message = "Deve ter pelo menos um email")
    @Valid
    private List<EmailDTORequest> listaEmail;
    @NotEmpty(message = "Deve ter pelo menos um telefone")
    @Valid
    private List<TelefoneDTORequest> listaTelefone;
}
