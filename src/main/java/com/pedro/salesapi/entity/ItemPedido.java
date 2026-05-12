package com.pedro.salesapi.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    private int quantidade;

    private double preco;
}