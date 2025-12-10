package com.desafiopicpay.picpay.services;

import com.desafiopicpay.picpay.domain.users.Users;
import com.desafiopicpay.picpay.domain.users.UsersDTO;
import com.desafiopicpay.picpay.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
