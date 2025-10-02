package com.techbank.accountcommand.application.commands;

import com.techbank.cqrscore.commands.BaseCommand;
import lombok.*;

@Data
public class WithdrawFoundsCommand extends BaseCommand {
    private double amount;
}
