package com.techbank.accountcommand.infrastructure;

import com.techbank.accountcommand.domain.EventStoreRepository;
import com.techbank.cqrscore.events.BaseEvent;
import com.techbank.cqrscore.events.EventModel;
import com.techbank.cqrscore.exceptions.AggregateNotFoundException;
import com.techbank.cqrscore.exceptions.ConcurrencyException;
import com.techbank.cqrscore.infrastructure.EventStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountEventStore implements EventStore {

    private final EventStoreRepository eventStoreRepository;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findAllByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
            throw new ConcurrencyException("Concurrency exception");
        }

        var version = expectedVersion;
        for (var event : events) {
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .timestamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(event.getClass().getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();
            var persistedEvent = eventStoreRepository.save(eventModel);

            if (persistedEvent != null) {
                //TODO: publish the event kafka
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = eventStoreRepository.findAllByAggregateIdentifier(aggregateId);
        if(eventStream == null || eventStream.isEmpty()){
            throw new AggregateNotFoundException("Incorrect account id provided");
        }
        return eventStream.stream().map(EventModel::getEventData).collect(Collectors.toList());
    }
}
