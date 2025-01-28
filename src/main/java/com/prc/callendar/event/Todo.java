package com.prc.callendar.event;

import java.time.ZonedDateTime;
import java.util.Set;

public class Todo extends AbstractEvent {

    private String description;

    public Todo(int id, String title, ZonedDateTime startAt, ZonedDateTime endAt,
                   String description) {
        super(id, title, startAt, endAt);
        this.description = description;
    }

    @Override
    public void print() {
        System.out.println("[할 일] " + getTitle() + " : " + description);
    }

    @Override
    public boolean support(EventType type) {
        return type == EventType.TO_DO ;
    }
}
