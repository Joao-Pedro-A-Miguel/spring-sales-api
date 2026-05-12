package com.pedro.salesapi.repository;

import com.pedro.salesapi.entity.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @EntityGraph(attributePaths = {"cliente"})
    List<Pedido> findAll();
}
