package br.com.dio.cepapi.repository;

import org.springframework.stereotype.Repository;
import br.com.dio.cepapi.model.Cliente;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    
}