package com.pedro.salesapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ClienteRequestDTO {

    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @Pattern(regexp = "\\d{11}", message = "CPF inválido")
    private String cpf;

    @Email(message = "Email inválido")
    private String email;
}
