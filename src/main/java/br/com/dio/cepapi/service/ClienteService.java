package br.com.dio.cepapi.service;

import br.com.dio.cepapi.model.Cliente;

public interface ClienteService {
    
    Iterable<Cliente> buscarTodosClientes();
    Cliente buscarClientePorId(Long id);
    void inserirCliente(Cliente cliente);
    void atualizarCliente(Long id, Cliente cliente);
    void deletarCliente(Long id);
}
