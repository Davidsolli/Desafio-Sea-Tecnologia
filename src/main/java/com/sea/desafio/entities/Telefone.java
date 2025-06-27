package com.sea.desafio.entities;

import com.sea.desafio.enums.TipoTelefone;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private TipoTelefone telefone;
}
