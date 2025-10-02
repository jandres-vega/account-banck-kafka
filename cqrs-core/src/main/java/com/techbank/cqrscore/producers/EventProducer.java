package com.techbank.cqrscore.producers;

import com.techbank.cqrscore.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
