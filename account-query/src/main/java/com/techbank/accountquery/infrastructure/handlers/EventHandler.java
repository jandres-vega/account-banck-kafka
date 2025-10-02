package com.techbank.accountquery.infrastructure.handlers;


import com.techbank.accountcommon.events.AccountClosedEvent;
import com.techbank.accountcommon.events.AccountOpenedEvent;
import com.techbank.accountcommon.events.FundsDepositedEvent;
import com.techbank.accountcommon.events.FundsWithdrawEvent;

public interface EventHandler {

    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawEvent event);
    void on(AccountClosedEvent event);
}
