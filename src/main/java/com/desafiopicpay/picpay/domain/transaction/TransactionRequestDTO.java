package com.desafiopicpay.picpay.domain.transaction;

import java.util.UUID;

public record TransactionRequestDTO(UUID payerID, UUID payeeID, Long value) {
}
