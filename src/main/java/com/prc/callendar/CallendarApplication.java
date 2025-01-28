package com.prc.callendar;

import com.prc.callendar.event.*;
import com.prc.callendar.event.update.UpdateMeeting;
import com.prc.callendar.reader.EventCsvReader;
import com.prc.callendar.reader.RawCsvReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

//@SpringBootApplication
public class CallendarApplication {

    public static void main(String[] args) throws IOException {
        Schedule schedule = new Schedule();

        EventCsvReader csvReader = new EventCsvReader(new RawCsvReader());
        String meetingCsvPath = "/data/meeting.csv";

        List<Meeting> meetings = csvReader.readMeetings(meetingCsvPath);
        meetings.forEach(schedule::add);
        Meeting meeting = meetings.get(0);
        meeting.print();
        System.out.println("수정");
        meetings.get(0).validateAndUpdate(
                new UpdateMeeting(
                        "new title",
                        ZonedDateTime.now(),
                        ZonedDateTime.now().plusHours(1),
                        null,
                        "A",
                        "new agenda"
                )
        );
        meeting.print();
        meeting.delete(true);
        System.out.println("삭제 후 수정");
        meetings.get(0).validateAndUpdate(
                new UpdateMeeting(
                        "delete title",
                        ZonedDateTime.now(),
                        ZonedDateTime.now().plusHours(1),
                        null,
                        "delete",
                        "delete agenda"
                )
        );
        meeting.print();
    }
}
