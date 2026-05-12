package com.pedro.salesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private double preco;
    private int quantidade;
}
