package br.com.dio.cepapi.configuration.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClienteIdException extends RuntimeException{
    
    public ClienteIdException(Long id) {
        super("O cliente que possui o id " + id + " não foi encontrado");
    }
}
