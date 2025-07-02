package com.sea.desafio.controller;

import com.sea.desafio.dtos.usuario.AuthenticationDTO;
import com.sea.desafio.dtos.usuario.CadastroDTO;
import com.sea.desafio.infrastructure.entities.Usuario;
import com.sea.desafio.infrastructure.repositories.UsuarioRepository;
import com.sea.desafio.infrastructure.security.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody AuthenticationDTO authenticationDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticationDTO.getEmail(), authenticationDTO.getSenha()
        );

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastro(@Valid @RequestBody CadastroDTO cadastroDTO) {

        if (this.usuarioRepository.findByEmail(cadastroDTO.getEmail()) != null) return ResponseEntity.badRequest().build();

        String senhaEncriptada = new BCryptPasswordEncoder().encode(cadastroDTO.getSenha());

        Usuario usuario = new Usuario(cadastroDTO.getEmail(), senhaEncriptada, cadastroDTO.getRole());

        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
