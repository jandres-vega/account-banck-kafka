package com.techbank.accountcommand.application.commands;

import com.techbank.cqrscore.commands.BaseCommand;
import lombok.*;

@Data
public class DepositFundsCommand extends BaseCommand {
    private double amount;
}
