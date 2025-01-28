package com.prc.callendar.event.update;

import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Set;

@Getter
public class UpdateMeeting extends AbstractAuditableEvent {

    private final Set<String> participants;
    private final String meetingRoom;
    private final String agenda;

    public UpdateMeeting(String title, ZonedDateTime startAt, ZonedDateTime endAt,
                            Set<String> participants, String meetingRoom, String agenda) {
        super(title, startAt, endAt);

        this.participants = participants;
        this.meetingRoom = meetingRoom;
        this.agenda = agenda;
    }
}
