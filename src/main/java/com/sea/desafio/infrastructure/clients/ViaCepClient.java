package com.sea.desafio.infrastructure.clients;

import com.sea.desafio.dtos.ViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "via-cep", url = "${viacep.url}")
public interface ViaCepClient {

    @GetMapping("/ws/{cep}/json/")
    ViaCepDTO encontrarEnderecoPorCep(@PathVariable("cep") String cep);
}
