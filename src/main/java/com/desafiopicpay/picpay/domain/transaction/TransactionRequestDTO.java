package com.desafiopicpay.picpay.domain.transaction;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRequestDTO(UUID payerID, UUID payeeID, BigDecimal value) {
}
