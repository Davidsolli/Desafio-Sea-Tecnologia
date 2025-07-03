package com.sea.desafio.controller;

import com.sea.desafio.dtos.telefone.request.TelefoneDTORequest;
import com.sea.desafio.dtos.telefone.response.TelefoneDTOResponse;
import com.sea.desafio.services.TelefoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/telefone")
@RequiredArgsConstructor
public class TelefoneController {

    private final TelefoneService telefoneService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{clienteId}")
    public ResponseEntity<List<TelefoneDTOResponse>> salvarNovosTelefones(
            @Valid @RequestBody List<TelefoneDTORequest> telefoneDTORequestLista,
            @PathVariable Long clienteId
    ) {
        return ResponseEntity.ok(telefoneService.salvarNovosTelefones(telefoneDTORequestLista, clienteId));
    }

    @GetMapping("/{telefoneId}")
    public ResponseEntity<TelefoneDTOResponse> encontrarTelefonePorId(@PathVariable Long telefoneId) {
        return ResponseEntity.ok(telefoneService.encontrarTelefonePorId(telefoneId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{telefoneId}")
    public ResponseEntity<Void> deletarTelefonePorId(@PathVariable Long telefoneId) {
        telefoneService.deletarTelefonePorId(telefoneId);
        return ResponseEntity.ok().build();
    }
}