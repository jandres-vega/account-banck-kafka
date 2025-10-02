package com.techbank.accountcommand.application.commands;

import com.techbank.cqrscore.commands.BaseCommand;

public class CloseAccountCommand extends BaseCommand {

    public CloseAccountCommand(String id) {
        super(id);
    }
}
