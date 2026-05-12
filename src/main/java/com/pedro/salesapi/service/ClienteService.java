package com.pedro.salesapi.service;

import com.pedro.salesapi.dto.ClienteRequestDTO;
import com.pedro.salesapi.dto.ClienteResponseDTO;
import com.pedro.salesapi.entity.Cliente;
import com.pedro.salesapi.exception.RegraNegocioException;
import com.pedro.salesapi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO clienteDTO){

        if (repository.existsByCpf(clienteDTO.getCpf())) {
            throw new RegraNegocioException("CPF já cadastrado");
        }

        if (repository.existsByEmail(clienteDTO.getEmail())) {
            throw new RegraNegocioException("E-mail já cadastrado");
        }

        Cliente cliente = new Cliente();

        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail().trim().toLowerCase());

        Cliente salvo = repository.save(cliente);

        return converter(salvo);
    }

    public List<ClienteResponseDTO> listar(){

        return repository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Long id){

        Cliente cliente = repository.findById(id)
                .orElseThrow(() ->
                        new RegraNegocioException("Cliente não encontrado"));

        return converter(cliente);
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO clienteDTO){

        Cliente clienteBanco = repository.findById(id)
                .orElseThrow(() ->
                        new RegraNegocioException("Cliente não encontrado"));

        clienteBanco.setNome(clienteDTO.getNome());
        clienteBanco.setCpf(clienteDTO.getCpf());
        clienteBanco.setEmail(clienteDTO.getEmail().trim().toLowerCase());

        Cliente atualizado = repository.save(clienteBanco);

        return converter(atualizado);
    }

    public void deletar(Long id){

        Cliente cliente = repository.findById(id)
                .orElseThrow(() ->
                        new RegraNegocioException("Cliente não encontrado"));

        repository.delete(cliente);
    }

    private ClienteResponseDTO converter(Cliente cliente){

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail()
        );
    }
}