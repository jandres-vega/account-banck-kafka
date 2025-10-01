package com.techbank.accountcommand.application.commands;

import com.techbank.accountcommon.dto.AccountType;
import com.techbank.cqrscore.commands.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OpenAccountCommand extends BaseCommand {

    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;

    public OpenAccountCommand(String id, String accountHolder, AccountType accountType, double openingBalance) {
        super(id);
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.openingBalance = openingBalance;
    }
}
