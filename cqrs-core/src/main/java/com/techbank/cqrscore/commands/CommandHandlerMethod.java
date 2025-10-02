package com.techbank.cqrscore.commands;

@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {
    void handle(BaseCommand command);
}

