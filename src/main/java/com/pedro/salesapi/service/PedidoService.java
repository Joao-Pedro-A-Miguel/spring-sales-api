package com.pedro.salesapi.service;

import com.pedro.salesapi.dto.PedidoResponseDTO;
import com.pedro.salesapi.exception.RegraNegocioException;
import com.pedro.salesapi.entity.*;
import com.pedro.salesapi.repository.ClienteRepository;
import com.pedro.salesapi.repository.PedidoRepository;
import com.pedro.salesapi.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Pedido salvar(Pedido pedido){

        if (pedido == null) {
            throw new RegraNegocioException("Pedido não pode ser nulo");
        }

        if (pedido.getCliente() == null){
            throw new RegraNegocioException("Cliente é obrigatório");
        }

        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new RegraNegocioException("Pedido deve ter itens");
        }

        Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));

        pedido.setCliente(cliente);

        double total = 0.0;

        for (ItemPedido item : pedido.getItens()) {

            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new RegraNegocioException("Produto não encontrado"));

            if (item.getQuantidade() <= 0) {
                throw new RegraNegocioException("Quantidade deve ser maior que zero");
            }

            item.setProduto(produto);

            item.setPreco(produto.getPreco());

            if (produto.getQuantidade() < item.getQuantidade()) {
                throw new RegraNegocioException("Estoque insuficiente");
            }

            total += produto.getPreco() * item.getQuantidade();

            produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());

            produtoRepository.save(produto);
        }

        pedido.setValorTotal(total);

        pedido.setData(LocalDate.now());

        return pedidoRepository.save(pedido);
    }

    public PedidoResponseDTO converter(Pedido pedido){
        PedidoResponseDTO dto = new PedidoResponseDTO();

        dto.setId(pedido.getId());
        dto.setData(pedido.getData());
        dto.setValorTotal(pedido.getValorTotal());
        dto.setNomeCliente(pedido.getCliente().getNome());

        return dto;
    }

    public List<PedidoResponseDTO> listar(){

        List<Pedido> pedidos = pedidoRepository.findAll();

        return pedidos.stream()
                .map(this::converter)
                .toList();
    }

    public PedidoResponseDTO buscarPorId(Long id){
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Pedido não encontrado"));

        return converter(pedido);
    }
}
