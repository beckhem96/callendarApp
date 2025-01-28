package com.prc.callendar.event;

public interface Event {
    void print();

    boolean support(EventType type);
}
