package com.pedro.salesapi.repository;

import com.pedro.salesapi.entity.Pedidos;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedidos, Long> {

    @EntityGraph(attributePaths = {"cliente"})
    List<Pedidos> findAll();
}
