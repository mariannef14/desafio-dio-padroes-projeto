package br.com.dio.cepapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.dio.cepapi.model.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String>{
    
}
