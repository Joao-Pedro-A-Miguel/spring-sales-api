package com.pedro.salesapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProdutoRequestDTO {
    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @Min(value = 1, message = "Preço inválido")
    private double preco;

    @Min(value = 0, message = "Quantidade inválida")
    private int quantidade;
}
