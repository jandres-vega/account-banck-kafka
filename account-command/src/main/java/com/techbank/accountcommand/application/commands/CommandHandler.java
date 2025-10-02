package com.techbank.accountcommand.application.commands;



public interface CommandHandler {
    void handle(OpenAccountCommand command);
    void handle(DepositFundsCommand command);
    void handle(WithdrawFoundsCommand command);
    void handle(CloseAccountCommand command);
}
