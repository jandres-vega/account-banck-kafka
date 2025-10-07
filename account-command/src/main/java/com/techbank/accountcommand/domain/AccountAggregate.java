package com.techbank.accountcommand.domain;

import com.techbank.accountcommand.application.commands.OpenAccountCommand;
import com.techbank.accountcommon.events.AccountClosedEvent;
import com.techbank.accountcommon.events.AccountOpenedEvent;
import com.techbank.accountcommon.events.FundsDepositedEvent;
import com.techbank.accountcommon.events.FundsWithdrawEvent;
import com.techbank.cqrscore.domain.AggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {

    @Getter
    public Boolean active;
    @Getter
    private double balance;

    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolder(command.getAccountHolder())
                .createdAt(LocalDate.EPOCH)
                .accountType(command.getAccountType())
                .openingBalance(command.getOpeningBalance())
                .build());
    }

    public void apply(AccountOpenedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFounds(double amount) {
        if(!this.active) {
            throw new IllegalArgumentException("Cannot deposit without active account");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Cannot deposit without amount");
        }
        raiseEvent(FundsDepositedEvent.builder().id(id).amount(amount).build());
    }

    public void apply(FundsDepositedEvent event) {
        this.id = event.getId();
        this.balance = event.getAmount();
    }

    public void withdrawFunds(double amount) {
        if(!this.active) {
            throw new IllegalArgumentException("Cannot withdraw without active account");
        }

        raiseEvent(FundsWithdrawEvent.builder().id(id).amount(amount).build());
    }

    public void apply(FundsWithdrawEvent event) {
        this.id = event.getId();
        this.balance = event.getAmount();
    }

    public void closeAccount() {
        if(!this.active) {
            throw new IllegalArgumentException("Cannot close without active account");
        }
        raiseEvent(AccountClosedEvent.builder().id(id).build());
    }

    public void apply(AccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
    }
}
