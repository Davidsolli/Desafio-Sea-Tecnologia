package com.sea.desafio.dtos;

import com.sea.desafio.dtos.email.request.EmailDTORequest;
import com.sea.desafio.dtos.endereco.request.EnderecoDTORequest;
import com.sea.desafio.dtos.telefone.request.TelefoneDTORequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private String nome;
    private String cpf;
    private List<EnderecoDTORequest> listaEndereco;
    private List<EmailDTORequest> listaEmail;
    private List<TelefoneDTORequest> listaTelefone;
}
