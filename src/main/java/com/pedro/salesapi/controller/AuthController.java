package com.pedro.salesapi.controller;

import com.pedro.salesapi.dto.LoginRequestDTO;
import com.pedro.salesapi.dto.LoginResponseDTO;
import com.pedro.salesapi.dto.UsuarioRequestDTO;
import com.pedro.salesapi.dto.UsuarioResponseDTO;
import com.pedro.salesapi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> register(@RequestBody @Valid UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.salvar(dto));
    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody @Valid LoginRequestDTO dto
    ) {
        return usuarioService.login(dto);
    }
}