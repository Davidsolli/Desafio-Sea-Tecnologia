package com.sea.desafio.controller;

import com.sea.desafio.dtos.cliente.request.ClienteDTORequest;
import com.sea.desafio.dtos.cliente.response.ClienteDTOResponse;
import com.sea.desafio.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTOResponse> salvarNovoCliente(
            @Valid @RequestBody ClienteDTORequest clienteDTORequest
    ) {
        return ResponseEntity.ok(clienteService.salvarNovoCliente(clienteDTORequest));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTOResponse>> listaClientes() {
        return ResponseEntity.ok( clienteService.listaClientes());
    }
}
