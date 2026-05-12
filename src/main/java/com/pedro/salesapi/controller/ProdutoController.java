package com.pedro.salesapi.controller;

import com.pedro.salesapi.dto.ProdutoRequestDTO;
import com.pedro.salesapi.dto.ProdutoResponseDTO;
import com.pedro.salesapi.entity.Produto;
import com.pedro.salesapi.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ProdutoResponseDTO> salvar(
            @RequestBody @Valid ProdutoRequestDTO dto
    ) {

        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());

        Produto salvo = produtoService.salvar(produto);

        ProdutoResponseDTO response = new ProdutoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getPreco(),
                salvo.getQuantidade()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public List<ProdutoResponseDTO> listaProduto() {

        return produtoService.listar()
                .stream()
                .map(produto -> new ProdutoResponseDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidade()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id) {

        Produto produto = produtoService.buscarPorId(id);

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade()
        );
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO produtoDTO) {

        Produto produto = new Produto();

        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidade(produtoDTO.getQuantidade());

        Produto atualizado = produtoService.atualizar(id, produto);

        return new ProdutoResponseDTO(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getPreco(),
                atualizado.getQuantidade()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        produtoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}