package com.desafiopicpay.picpay.domain.users;

import java.math.BigDecimal;

public record UsersDTO(
        String firstName,
        String lastName,
        String CPF,
        String email,
        String password,
        BigDecimal balance,
        UserType userType) {
}
