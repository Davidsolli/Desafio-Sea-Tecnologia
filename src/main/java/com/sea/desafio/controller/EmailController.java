package com.sea.desafio.controller;

import com.sea.desafio.dtos.email.request.EmailDTORequest;
import com.sea.desafio.dtos.email.response.EmailDTOResponse;
import com.sea.desafio.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{clienteId}")
    public ResponseEntity<List<EmailDTOResponse>> salvarNovosEmails(
            @Valid @RequestBody List<EmailDTORequest> emailDTORequestLista, @PathVariable Long clienteId
    ) {
        return ResponseEntity.ok(emailService.salvarNovosEmails(emailDTORequestLista, clienteId));
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<EmailDTOResponse> encontrarEmailPorId(@PathVariable Long emailId) {
        return ResponseEntity.ok(emailService.encontrarEmailPorId(emailId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{emailId}")
    public ResponseEntity<Void> deletarEmailPorId(@PathVariable Long emailId) {
        emailService.deletarEmailPorId(emailId);
        return ResponseEntity.ok().build();
    }
}
