package com.pedro.salesapi.dto;

import com.pedro.salesapi.entity.Role;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        Role role
) {
}
