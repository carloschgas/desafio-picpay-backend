package com.desafiopicpay.picpay.domain.users;

public record UsersDTO(
        String firstName,
        String lastName,
        String CPF,
        String email,
        String password,
        Long balance,
        UserType userType) {
}
