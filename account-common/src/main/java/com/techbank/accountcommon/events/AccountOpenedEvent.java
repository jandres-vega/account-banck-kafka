package com.techbank.accountcommon.events;

import com.techbank.accountcommon.dto.AccountType;
import com.techbank.cqrscore.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountOpenedEvent extends BaseEvent {

    private String accountHolder;
    private AccountType accountType;
    private LocalDate createdAt;
    private double openingBalance;
}
