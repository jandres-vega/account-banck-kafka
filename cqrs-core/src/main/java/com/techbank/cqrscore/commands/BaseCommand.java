package com.techbank.cqrscore.commands;

import com.techbank.cqrscore.messages.Message;
import lombok.*;

@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {
    public BaseCommand(String id) {
        super(id);
    }
}
