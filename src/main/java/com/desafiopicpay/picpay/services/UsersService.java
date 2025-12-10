package com.desafiopicpay.picpay.services;

import com.desafiopicpay.picpay.domain.users.Users;
import com.desafiopicpay.picpay.domain.users.UsersDTO;
import com.desafiopicpay.picpay.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users createUser(UsersDTO data) throws Exception {

        if(usersRepository.findUserByCPF(data.CPF()).isPresent() || usersRepository.findUsersByEmail(data.email()).isPresent()){
            throw new Exception("Atenção: Já existe um dos identificadores cadastrados");
        }

        Users newUser = new Users(data.firstName(), data.lastName(), data.CPF(), data.email(), data.password(), data.balance(), data.userType());
        usersRepository.save(newUser);
        return newUser;
    }

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public Users findUserById(UUID userID) throws Exception {
        return usersRepository.findById(userID).orElseThrow(() -> new Exception("Usuário de ID" + userID + " não encontrado"));
    }

    public void validateTransaction(UUID payerID, BigDecimal value) throws Exception {
        Users payer = findUserById(payerID);

        if (payer.getUserType().equals("LOJISTA")){
            throw new Exception("Lojistas não podem fazer transações, apenas receber");
        }

        if (value.compareTo(payer.getBalance()) > 0){
            throw new Exception("Valor da transação é maior que o saldo do usuário");
        }
    }

    public void addBalance(Users user, BigDecimal value){
        user.setBalance(user.getBalance().add(value));
    }

    public void subBalance(Users user, BigDecimal value) throws Exception{
        BigDecimal newBalance = user.getBalance().subtract(value);

        if (newBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new Exception("O saldo não pode ficar negativo após a transação");
        }

        user.setBalance(newBalance);
    }
}
