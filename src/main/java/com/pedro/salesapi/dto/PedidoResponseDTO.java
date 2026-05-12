package com.pedro.salesapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PedidoResponseDTO {

    private Long id;
    private LocalDate data;
    private Double valorTotal;
    private String nomeCliente;

}
