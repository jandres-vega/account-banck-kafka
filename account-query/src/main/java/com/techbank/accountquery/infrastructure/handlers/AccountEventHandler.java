package com.techbank.accountquery.infrastructure.handlers;

import com.techbank.accountcommon.events.AccountClosedEvent;
import com.techbank.accountcommon.events.AccountOpenedEvent;
import com.techbank.accountcommon.events.FundsDepositedEvent;
import com.techbank.accountcommon.events.FundsWithdrawEvent;
import com.techbank.accountquery.domain.AccountRepository;
import com.techbank.accountquery.domain.BankAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class AccountEventHandler implements EventHandler{

    private final AccountRepository accountRepository;

    public AccountEventHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void on(AccountOpenedEvent event) {
        var bankAccount = BankAccount.builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .createdDate(event.getCreatedAt())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();

        accountRepository.save(bankAccount);
    }

    @Override
    public void on(FundsDepositedEvent event) {
        var bankAccount = accountRepository.findById(event.getId());
        if (bankAccount.isEmpty()) {
            return;
        }
        var currentBalance = bankAccount.get().getBalance();
        var lastBalance = currentBalance + event.getAmount();
        bankAccount.get().setBalance(lastBalance);
        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(FundsWithdrawEvent event) {
        var bankAccount = accountRepository.findById(event.getId());
        if (bankAccount.isEmpty()) {
            return;
        }
        var currentBalance = bankAccount.get().getBalance();
        var lastBalance = currentBalance - event.getAmount();
        bankAccount.get().setBalance(lastBalance);
        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}
