package com.pedro.salesapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gerente")
public class GerenteController {

    @GetMapping("/teste")
    public String teste() {
        return "Gerente autenticado";
    }
}