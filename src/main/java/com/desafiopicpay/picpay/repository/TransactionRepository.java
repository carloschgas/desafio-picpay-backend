package com.desafiopicpay.picpay.repository;

import com.desafiopicpay.picpay.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository <Transaction, UUID> {
}
