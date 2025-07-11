package com.sea.desafio.controller;

import com.sea.desafio.dtos.ViaCepDTO;
import com.sea.desafio.dtos.cliente.request.ClienteDTORequest;
import com.sea.desafio.dtos.cliente.request.ClienteMinDTORequest;
import com.sea.desafio.dtos.cliente.response.ClienteDTOResponse;
import com.sea.desafio.services.ClienteService;
import com.sea.desafio.services.ViaCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final ViaCepService viaCepService;

    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteDTOResponse> encontrarClientePorId(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clienteService.encontrarClientePorId(clienteId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteDTOResponse> atualizarDadosDeCliente(
            @Valid @RequestBody ClienteMinDTORequest clienteMinDTORequest, @PathVariable Long clienteId
    ) {
        return ResponseEntity.ok(clienteService.atualizarDadosDeCliente(clienteMinDTORequest, clienteId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long clienteId) {
        clienteService.excluirCliente(clienteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/endereco/{cep}")
    public ResponseEntity<ViaCepDTO> findAddressData(@PathVariable("cep") String cep) {
        return ResponseEntity.ok(viaCepService.encontrarEnderecoPorCep(cep));
    }
}
