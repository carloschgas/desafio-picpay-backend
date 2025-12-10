package com.desafiopicpay.picpay.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity duplicateEntry (DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário já cadastrado", "400");
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound (EntityNotFoundException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário não encontrado", "404");
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedTransactionException.class)
    public ResponseEntity unauthorized (UnauthorizedTransactionException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Internal Server Error", "500");
        return new ResponseEntity<>(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler (Exception.class)
    public ResponseEntity generalException(Exception exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "400");
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

}
