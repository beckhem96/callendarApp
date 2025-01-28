package com.prc.callendar.event;

import java.time.ZonedDateTime;

public class OutOfOffice extends AbstractEvent{

    public OutOfOffice(int id, String title, ZonedDateTime startAt, ZonedDateTime endAt) {
        super(id, title, startAt, endAt);
    }

    @Override
    public boolean support(EventType type) {
        return type == EventType.OUT_OF_OFFICE;
    }

    @Override
    public void print() {

    }

}
