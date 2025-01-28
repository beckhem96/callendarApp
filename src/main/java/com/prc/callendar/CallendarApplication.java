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
        String noDisturbanceCsvPath = "/data/no_disturbance.csv";
        String outOfOfficeCsvPath = "/data/out_of_office.csv";
        String toDoCsvPath = "/data/to_do.csv";

        List<Meeting> meetings = csvReader.readMeetings(meetingCsvPath);
        meetings.forEach(schedule::add);

        List<NoDisturbance> noDisturbances = csvReader.readNoDisturbance(noDisturbanceCsvPath);
        noDisturbances.forEach(schedule::add);

        List<OutOfOffice> outOfOffices= csvReader.readOutOfOffices(outOfOfficeCsvPath);
        outOfOffices.forEach(schedule::add);

        List<Todo> todos = csvReader.readToDos(toDoCsvPath);
        todos.forEach(schedule::add);

        schedule.printAll();
    }
}
