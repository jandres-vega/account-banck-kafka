package com.techbank.accountquery.domain;

import com.techbank.accountcommon.dto.AccountType;
import com.techbank.cqrscore.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;


@Builder
@Entity
public class BankAccount extends BaseEntity {

    @Id
    private String id;
    private String accountHolder;
    private LocalDate createdDate;
    private AccountType accountType;
    private double balance;

    public BankAccount(String id, String accountHolder, LocalDate createdDate, AccountType accountType, double balance) {
        this.id = id;
        this.accountHolder = accountHolder;
        this.createdDate = createdDate;
        this.accountType = accountType;
        this.balance = balance;
    }

    public BankAccount() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
