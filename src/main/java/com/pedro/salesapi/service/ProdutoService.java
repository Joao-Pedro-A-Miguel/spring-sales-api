package com.pedro.salesapi.service;


import com.pedro.salesapi.Exception.RegraNegocioException;
import com.pedro.salesapi.entity.Produto;
import com.pedro.salesapi.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto salvar(Produto produto){
        return repository.save(produto);
    }

    public List<Produto> listar(){
        return repository.findAll();
    }

    public Produto buscarPorId(long id){
        return repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Produto não encontrado"));
    }

    public Produto atualizar(Long id, Produto produto){

        Produto produtoBanco = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Produto não encontrado"));

        produtoBanco.setNome(produto.getNome());
        produtoBanco.setPreco(produto.getPreco());
        produtoBanco.setQuantidade(produto.getQuantidade());

        return repository.save(produtoBanco);
    }

    public void deletar(Long id){
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Produto não encontrado"));

        repository.delete(produto);
    }
 }
