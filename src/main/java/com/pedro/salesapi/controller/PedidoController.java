package com.pedro.salesapi.controller;


import com.pedro.salesapi.dto.PedidoResponseDTO;
import com.pedro.salesapi.entity.Pedido;
import com.pedro.salesapi.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> salvar(@RequestBody @Valid Pedido pedido){

        Pedido salvo = pedidoService.salvar(pedido);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.converter(salvo));
    }

    @GetMapping
    public List<PedidoResponseDTO> listar(){
        return pedidoService.listar();
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO buscar(@PathVariable Long id){
        return pedidoService.buscarPorId(id);
    }

}
