package com.techbank.accountcommand.application.commands;

import com.techbank.cqrscore.commands.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WithdrawFoundsCommand extends BaseCommand {
    private double amount;

    public WithdrawFoundsCommand(String id, double amount) {
        super(id);
        this.amount = amount;
    }
}
