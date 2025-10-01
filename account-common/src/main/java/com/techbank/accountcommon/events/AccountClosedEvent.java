package com.techbank.accountcommon.events;


import com.techbank.cqrscore.events.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class AccountClosedEvent extends BaseEvent {
}
