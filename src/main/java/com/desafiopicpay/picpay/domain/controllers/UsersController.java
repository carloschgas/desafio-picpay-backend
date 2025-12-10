package com.desafiopicpay.picpay.domain.controllers;

import com.desafiopicpay.picpay.domain.users.Users;
import com.desafiopicpay.picpay.domain.users.UsersDTO;
import com.desafiopicpay.picpay.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService service){
        this.usersService = service;
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UsersDTO data) throws Exception {
        Users newUser = usersService.createUser(data);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Users>>getAllUsers(){
        List<Users> listOfUsers = usersService.getAllUsers();
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }
}
