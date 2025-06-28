package com.sea.desafio.dtos.cliente.request;

import com.sea.desafio.dtos.email.request.EmailDTORequest;
import com.sea.desafio.dtos.endereco.request.EnderecoDTORequest;
import com.sea.desafio.dtos.telefone.request.TelefoneDTORequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTORequest {

    private String nome;
    private String cpf;
    private List<EnderecoDTORequest> listaEndereco;
    private List<EmailDTORequest> listaEmail;
    private List<TelefoneDTORequest> listaTelefone;
}
