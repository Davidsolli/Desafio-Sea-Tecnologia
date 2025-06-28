package com.sea.desafio.dtos.cliente.response;

import com.sea.desafio.dtos.email.response.EmailDTOResponse;
import com.sea.desafio.dtos.endereco.response.EnderecoDTOResponse;
import com.sea.desafio.dtos.telefone.response.TelefoneDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTOResponse {

    private String nome;
    private String cpf;
    private List<EnderecoDTOResponse> listaEndereco;
    private List<EmailDTOResponse> listaEmail;
    private List<TelefoneDTOResponse> listaTelefone;
}
