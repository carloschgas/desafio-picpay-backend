package com.desafiopicpay.picpay.controllers;

import com.desafiopicpay.picpay.domain.transaction.Transaction;
import com.desafiopicpay.picpay.domain.transaction.TransactionRequestDTO;
import com.desafiopicpay.picpay.domain.transaction.TransactionResponseDTO;
import com.desafiopicpay.picpay.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction (@RequestBody TransactionRequestDTO data) throws Exception{
        TransactionResponseDTO newTransaction = transactionService.createTransaction(data);

        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
}
