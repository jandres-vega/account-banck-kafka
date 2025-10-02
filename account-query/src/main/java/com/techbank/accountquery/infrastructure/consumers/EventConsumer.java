package com.techbank.accountquery.infrastructure.consumers;

import com.techbank.accountcommon.events.AccountClosedEvent;
import com.techbank.accountcommon.events.AccountOpenedEvent;
import com.techbank.accountcommon.events.FundsDepositedEvent;
import com.techbank.accountcommon.events.FundsWithdrawEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload AccountOpenedEvent event, Acknowledgment ack);
    void consume(@Payload FundsDepositedEvent event, Acknowledgment ack);
    void consume(@Payload FundsWithdrawEvent event, Acknowledgment ack);
    void consume(@Payload AccountClosedEvent event, Acknowledgment ack);
}
