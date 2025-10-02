package com.techbank.accountcommand.application.commands;

import com.techbank.accountcommon.dto.AccountType;
import com.techbank.cqrscore.commands.BaseCommand;
import lombok.*;

@Data
public class OpenAccountCommand extends BaseCommand {

    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
