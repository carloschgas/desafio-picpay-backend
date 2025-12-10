package com.desafiopicpay.picpay.services;


import com.desafiopicpay.picpay.domain.transaction.Transaction;
import com.desafiopicpay.picpay.domain.transaction.TransactionRequestDTO;
import com.desafiopicpay.picpay.domain.transaction.TransactionResponseDTO;
import com.desafiopicpay.picpay.domain.users.Users;
import com.desafiopicpay.picpay.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UsersService usersService;
    private final RestTemplate restTemplate;

    public TransactionService(TransactionRepository repository, UsersService usersService, RestTemplate restTemplate){
        this.transactionRepository = repository;
        this.usersService = usersService;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public TransactionResponseDTO createTransaction(TransactionRequestDTO data) throws Exception{

        Users payer = usersService.findUserById(data.payerID());
        Users payee = usersService.findUserById(data.payeeID());

        usersService.validateTransaction(data.payerID(), data.value());

        boolean isAuthorized = isTransactionAuthorized();
        if (!isAuthorized){
            throw new Exception("Transação não autorizada externamente");
        }

        usersService.subBalance(payer,data.value());
        usersService.addBalance(payee, data.value());

        Transaction newTransaction = new Transaction(payer, payee, data.value());
        transactionRepository.save(newTransaction);

        return new TransactionResponseDTO(newTransaction.getTransactionID(), payer.getUserID(), payee.getUserID(), newTransaction.getAmount());
    }

    public boolean isTransactionAuthorized() {

        /*
        Como o link externo do desafio está offline no momento em que estou realizando, deixei tudo comentado e um
        return true pra execução do programa sem problemas

        ResponseEntity<Map> res = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if (res.getStatusCode() == HttpStatus.OK){
            if (res.getBody().get("message") == "Autorizado"){
                return true;
            }
        }
        return false;

        */

        return true;
    }

}
