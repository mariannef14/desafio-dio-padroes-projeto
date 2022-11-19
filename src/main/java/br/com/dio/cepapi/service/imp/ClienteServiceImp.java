package br.com.dio.cepapi.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dio.cepapi.configuration.exceptions.ClienteIdException;
import br.com.dio.cepapi.model.Cliente;
import br.com.dio.cepapi.model.Endereco;
import br.com.dio.cepapi.repository.ClienteRepository;
import br.com.dio.cepapi.repository.EnderecoRepository;
import br.com.dio.cepapi.service.ClienteService;
import br.com.dio.cepapi.service.ViaCepService;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarClientePorId(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (!cliente.isPresent()){
            throw new ClienteIdException(id);
        }
        
        return cliente.get();
    }

    @Override
    public void inserirCliente(Cliente cliente) {
        salvarClienteComEndereco(cliente);
    }

    @Override
    public void atualizarCliente(Long id, Cliente cliente){
        Optional<Cliente> clienteId = clienteRepository.findById(id);

        if (clienteId.isPresent())
            salvarClienteComEndereco(cliente);
        else
            throw new ClienteIdException(id);
    }

    @Override
    public void deletarCliente(Long id){
        Optional<Cliente> clienteId = clienteRepository.findById(id);

        if (clienteId.isPresent())
            clienteRepository.deleteById(id);
        else
            throw new ClienteIdException(id);
    }

    public void salvarClienteComEndereco(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);

            return novoEndereco;
        });

        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);
    }
}
