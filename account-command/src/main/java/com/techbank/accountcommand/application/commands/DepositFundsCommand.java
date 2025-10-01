package com.techbank.accountcommand.application.commands;

import com.techbank.cqrscore.commands.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DepositFundsCommand extends BaseCommand {

    private double amount;

    public DepositFundsCommand(String id, double amount) {
        super(id);
        this.amount = amount;
    }
}
