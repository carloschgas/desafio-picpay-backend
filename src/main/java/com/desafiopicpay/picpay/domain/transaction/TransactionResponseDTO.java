package com.desafiopicpay.picpay.domain.transaction;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionResponseDTO(
        UUID transactionID,
        UUID payerID,
        UUID payeeID,
        BigDecimal value) {

}
