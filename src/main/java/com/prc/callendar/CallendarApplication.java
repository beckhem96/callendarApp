package com.prc.callendar;

import com.prc.callendar.event.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

//@SpringBootApplication
public class CallendarApplication {

    public static void main(String[] args) {
        Schedule schedule = new Schedule();
        HashSet<String> participants = new HashSet<>();
        participants.add("wantaek.lim");
        Meeting meeting1 = new Meeting(
                1, "meeting1", ZonedDateTime.now(), ZonedDateTime.now().plusHours(1),
                participants, "meetingRoomA", "스터디"
        );

        schedule.add(meeting1);

        Todo todo1 = new Todo(
            2, "todo1", ZonedDateTime.now().plusHours(3), ZonedDateTime.now().plusHours(4),
            "할 일 적기"
        );
        schedule.add(todo1);

        Todo todo2 = new Todo(
                3, "todo2", ZonedDateTime.now().plusHours(5), ZonedDateTime.now().plusHours(4),
                "할 일 적기"
        );
        schedule.add(todo2);

        schedule.printAll();
    }

}
