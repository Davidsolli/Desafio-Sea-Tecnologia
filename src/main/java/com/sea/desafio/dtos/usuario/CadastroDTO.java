package com.sea.desafio.dtos.usuario;

import com.sea.desafio.infrastructure.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroDTO {

    private String email;
    private String senha;
    private UserRole role;
}
