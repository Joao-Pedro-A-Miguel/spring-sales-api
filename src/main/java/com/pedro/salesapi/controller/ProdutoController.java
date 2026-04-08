package com.pedro.salesapi.controller;


import com.pedro.salesapi.entity.Produto;
import com.pedro.salesapi.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
        return ResponseEntity.ok(produtoService.salvar(produto));
    }

    @GetMapping
    public List<Produto> listaProduto(){
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id){
        return produtoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable long id,@RequestBody Produto produto) {
        return produtoService.atualizar(id,produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
