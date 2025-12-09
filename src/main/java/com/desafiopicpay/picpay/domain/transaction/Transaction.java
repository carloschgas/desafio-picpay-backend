package com.desafiopicpay.picpay.domain.transaction;

import com.desafiopicpay.picpay.domain.users.Users;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
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
    private Long value;

    public Transaction(){}

    public Transaction(Users payer, Users payee, Long value){
        this.payer = payer;
        this.payee = payee;
        this.value = value;
    }

    public Long getValue() {
        return value;
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

    public void setPayee(Users payee) {
        this.payee = payee;
    }

    public void setPayer(Users payer) {
        this.payer = payer;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
