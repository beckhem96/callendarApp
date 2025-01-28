package com.prc.callendar.event;

import java.time.ZonedDateTime;
import java.util.Set;

public class Meeting extends AbstractEvent {

    private Set<String> participanths;
    private String meetingRoom;
    private String agenda;
    public Meeting(int id, String title, ZonedDateTime startAt, ZonedDateTime endAt,
                   Set<String> participanths, String meetingRoom, String agenda) {
        super(id, title, startAt, endAt);
        this.participanths = participanths;
        this.meetingRoom = meetingRoom;
        this.agenda = agenda;
    }
    @Override
    public void print() {
        System.out.println("Meeting Room: " + getTitle() + " " + agenda);
    }

    @Override
    public boolean support(EventType type) {
        return type == EventType.MEETING;
    }
}
