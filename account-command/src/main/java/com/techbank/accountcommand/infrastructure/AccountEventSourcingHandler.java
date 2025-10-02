package com.techbank.accountcommand.infrastructure;

import com.techbank.accountcommand.domain.AccountAggregate;
import com.techbank.cqrscore.domain.AggregateRoot;
import com.techbank.cqrscore.events.BaseEvent;
import com.techbank.cqrscore.handlers.EventSourcingHandler;
import com.techbank.cqrscore.infrastructure.EventStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    private final EventStore eventStore;

    public AccountEventSourcingHandler(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void save(AggregateRoot aggregateRoot) {
        eventStore.saveEvents(aggregateRoot.getId(), aggregateRoot.getUncommittedChanges(), aggregateRoot.getVersion());
        aggregateRoot.markChangesAsCommitted();
    }

    @Override
    public AccountAggregate getById(String id) {
        var aggregate = new AccountAggregate();
        var events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
            return aggregate;
        }
        return aggregate;
    }


}
