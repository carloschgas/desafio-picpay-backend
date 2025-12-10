package com.desafiopicpay.picpay.repository;

import com.desafiopicpay.picpay.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
        Optional<Users> findUserByCPF(String CPF);
        Optional<Users> findUsersByEmail(String email);
}
