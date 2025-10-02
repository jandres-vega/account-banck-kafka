package com.techbank.accountcommand;

import com.techbank.accountcommand.application.commands.*;
import com.techbank.cqrscore.infrastructure.CommandDispatcher;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountCommandApplication {

    private final CommandDispatcher commandDispatcher;
    private final CommandHandler commandHandler;

    public AccountCommandApplication(CommandDispatcher commandDispatcher, CommandHandler commandHandler) {
        this.commandDispatcher = commandDispatcher;
        this.commandHandler = commandHandler;
    }

    public static void main(String[] args) {
        SpringApplication.run(AccountCommandApplication.class, args);
    }

    @PostConstruct
    public void registerHandlers() {
        commandDispatcher.registerHandler(OpenAccountCommand.class, cmd -> commandHandler.handle((OpenAccountCommand) cmd));
        commandDispatcher.registerHandler(DepositFundsCommand.class, cmd -> commandHandler.handle((DepositFundsCommand) cmd));
        commandDispatcher.registerHandler(WithdrawFoundsCommand.class, cmd -> commandHandler.handle((WithdrawFoundsCommand) cmd));
        commandDispatcher.registerHandler(CloseAccountCommand.class, cmd -> commandHandler.handle((CloseAccountCommand) cmd));
    }
}
