package com.pedro.salesapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @NotBlank(message = "CPF obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 números")
    private String cpf;

    @NotBlank(message = "Email obrigatório")
    @Email(message = "E-mail inválido")
    private String email;
}