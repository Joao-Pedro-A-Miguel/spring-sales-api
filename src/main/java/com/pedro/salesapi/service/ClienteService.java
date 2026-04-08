package com.pedro.salesapi.service;

import com.pedro.salesapi.Exception.RegraNegocioException;
import com.pedro.salesapi.entity.Cliente;
import com.pedro.salesapi.entity.ClienteResponseDTO;
import com.pedro.salesapi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente salvar(Cliente cliente){

        if (repository.existsByCpf(cliente.getCpf())) {
            throw new RegraNegocioException("CPF já cadastrado");
        }

        if (repository.existsByEmail(cliente.getEmail())) {
            throw new RegraNegocioException("E-mail já cadastrado");
        }

        if (cliente.getCpf().length() != 11){
            throw new RegraNegocioException("CPF deve conter 11 digitos");
        }

        if (!cliente.getEmail().contains("@")){
            throw new RegraNegocioException("E-mail inválido");
        }

        return repository.save(cliente);
    }

    public List<ClienteResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(cliente -> new ClienteResponseDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getEmail()
                ))
                .toList();
    }

    public Cliente buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));
    }

    public Cliente atualizar(Long id, Cliente cliente){

        Cliente clienteBanco = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));

        clienteBanco.setNome(cliente.getNome());
        clienteBanco.setCpf(cliente.getCpf());
        clienteBanco.setEmail(cliente.getEmail());

        return repository.save(clienteBanco);
    }

    public void deletar(Long id){
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));

        repository.delete(cliente);
    }
}