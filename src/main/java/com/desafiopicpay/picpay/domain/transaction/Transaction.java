package com.desafiopicpay.picpay.domain.transaction;

import com.desafiopicpay.picpay.domain.users.Users;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transactionID;
    @ManyToOne
    @JoinColumn(name = "payer_id")
    private Users payer;
    @ManyToOne
    @JoinColumn(name = "payee_id")
    private Users payee;
    private BigDecimal amount;

   public Transaction(){}

    public Transaction( Users payer, Users payee, BigDecimal amount){
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Users getPayee() {
        return payee;
    }

    public Users getPayer() {
        return payer;
    }

    public UUID getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(UUID transactionID) { this.transactionID = transactionID;}

    public void setPayee(Users payee) {
        this.payee = payee;
    }

    public void setPayer(Users payer) {
        this.payer = payer;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
