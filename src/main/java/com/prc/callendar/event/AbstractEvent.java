package com.prc.callendar.event;

import com.prc.callendar.event.update.AbstractAuditableEvent;
import com.prc.callendar.exception.InvalidEventException;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.ZonedDateTime;

@Getter
@Setter
public abstract class AbstractEvent implements Event {
    private final int id;
    private String title;
    private ZonedDateTime startAt;
    private ZonedDateTime endAt;
    private Duration duration;

    private final ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;

    private boolean deletedYn;

    protected AbstractEvent(int id, String title, ZonedDateTime startAt, ZonedDateTime endAt) {

        if (startAt.isAfter(endAt)) {
            throw new InvalidEventException("startAt must be after endAt");
        }

        this.id = id;
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.duration = Duration.between(startAt, endAt);

        ZonedDateTime now = ZonedDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;

        this.deletedYn = false;
    }

    public void validateAndUpdate(AbstractAuditableEvent update) {
        if (deletedYn) {
            throw new InvalidEventException("Event has been deleted");
        }

        defaultUpdate(update);
        update(update);
    }

    private void defaultUpdate(AbstractAuditableEvent update) {
        this.title = update.getTitle();
        this.startAt = update.getStartAt();
        this.endAt = update.getEndAt();
        this.duration = Duration.between(this.startAt, this.endAt);
        this.modifiedAt = ZonedDateTime.now();
    }

    public void delete(boolean deletedYn) {
        this.deletedYn = deletedYn;
    }

    protected abstract void update(AbstractAuditableEvent update);

    public abstract boolean support(EventType type);
}
