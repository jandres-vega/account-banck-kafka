package com.techbank.cqrscore.domain;

import com.techbank.cqrscore.events.BaseEvent;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {


    protected String id;
    private int version = -1;

    private final List<BaseEvent> changes = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(AggregateRoot.class);

    public String getId() {
        return this.id;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<BaseEvent> getUncommittedChanges() {
        return this.changes;
    }

    public void markChangesAsCommitted() {
        this.changes.clear();
    }

    protected void applyChange(BaseEvent event, boolean isNewEvent) {
        try {
            var method = this.getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            logger.warn("No apply method found for event: {}", event.getClass().getSimpleName());
        } catch (Exception e) {
            logger.error("Error applying event: {}", e.getMessage());
        }

        if (isNewEvent) {
            changes.add(event);
        }
    }

    public void raiseEvent(BaseEvent event) {
        applyChange(event, true);
    }

    public void replayEvents(Iterable<BaseEvent> events) {
        events.forEach(event -> applyChange(event, false));
    }

}
