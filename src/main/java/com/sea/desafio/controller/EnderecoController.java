package com.sea.desafio.controller;

import com.sea.desafio.dtos.endereco.request.EnderecoMinDTORequest;
import com.sea.desafio.dtos.endereco.response.EnderecoDTOResponse;
import com.sea.desafio.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{enderecoId}")
    public ResponseEntity<EnderecoDTOResponse> atualizarEndereco (
            @PathVariable Long enderecoId, @Valid @RequestBody EnderecoMinDTORequest enderecoMinDTORequest
    ) {
        return ResponseEntity.ok(enderecoService.atualizarEndereco(enderecoId, enderecoMinDTORequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/cep/{enderecoId}")
    public ResponseEntity<EnderecoDTOResponse> atualizarEnderecoPorCep(
            @PathVariable Long enderecoId, @RequestParam(value = "cep"
    ) String cep) {
        return ResponseEntity.ok(enderecoService.atualizarEnderecoPorCep(enderecoId, cep));
    }
}
