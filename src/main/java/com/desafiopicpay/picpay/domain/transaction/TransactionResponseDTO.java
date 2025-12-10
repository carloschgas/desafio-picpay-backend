package com.desafiopicpay.picpay.domain.transaction;

import java.util.UUID;

public record TransactionResponseDTO(
        UUID transactionID,
        UUID payerID,
        UUID payeeID,
        Long value) {

}
