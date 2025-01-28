package com.prc.callendar.reader;

import com.opencsv.CSVReader;
import com.prc.callendar.event.Meeting;
import com.prc.callendar.event.NoDisturbance;
import com.prc.callendar.event.OutOfOffice;
import com.prc.callendar.event.Todo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EventCsvReader {

    private final RawCsvReader rawCsvReader;

    public EventCsvReader(RawCsvReader rawCsvReader) {
        this.rawCsvReader = rawCsvReader;
    }

    public List<Meeting> readMeetings(String path) throws IOException {
        List<Meeting> result = new ArrayList<Meeting>();

        // 데이터를 읽는 부분
        List<String[]> read = rawCsvReader.readAll(path);

        for (int i = 0; i < read.size(); i++) {
            if (skipHeader(i)) {
                continue;
            }

            String[] row = read.get(i);

            result.add(
                    new Meeting(
                            Integer.parseInt(row[0]),
                            row[2],
                            ZonedDateTime.of(LocalDateTime.parse(
                                    row[6],
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                                    ZoneId.of("Asia/Seoul")),
                            ZonedDateTime.of(LocalDateTime.parse(row[7], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), ZoneId.of("Asia/Seoul")),
                            new HashSet<>(Arrays.asList(row[3].split(","))),
                            row[4],
                            row[5])
            );
        }

        return result;
    }

    public List<NoDisturbance> readNoDisturbance(String path) throws IOException {
        List<NoDisturbance> result = new ArrayList<NoDisturbance>();

        // 데이터를 읽는 부분
        List<String[]> read = rawCsvReader.readAll(path);

        for (int i = 0; i < read.size(); i++) {
            if (skipHeader(i)) {
                continue;
            }

            String[] row = read.get(i);

            result.add(
                    new NoDisturbance(
                            Integer.parseInt(row[0]),
                            row[2],
                            ZonedDateTime.of(LocalDateTime.parse(
                                            row[3],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                                    ZoneId.of("Asia/Seoul")),
                            ZonedDateTime.of(LocalDateTime.parse(row[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), ZoneId.of("Asia/Seoul")))
            );
        }

        return result;
    }

    public List<OutOfOffice> readOutOfOffices(String path) throws IOException {
        List<OutOfOffice> result = new ArrayList<OutOfOffice>();

        // 데이터를 읽는 부분
        List<String[]> read = rawCsvReader.readAll(path);

        for (int i = 0; i < read.size(); i++) {
            if (skipHeader(i)) {
                continue;
            }

            String[] row = read.get(i);

            result.add(
                    new OutOfOffice(
                            Integer.parseInt(row[0]),
                            row[2],
                            ZonedDateTime.of(LocalDateTime.parse(
                                            row[3],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                                    ZoneId.of("Asia/Seoul")),
                            ZonedDateTime.of(LocalDateTime.parse(row[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), ZoneId.of("Asia/Seoul")))
            );
        }

        return result;
    }

    public List<Todo> readToDos(String path) throws IOException {
        List<Todo> result = new ArrayList<Todo>();

        // 데이터를 읽는 부분
        List<String[]> read = rawCsvReader.readAll(path);

        for (int i = 0; i < read.size(); i++) {
            if (skipHeader(i)) {
                continue;
            }

            String[] row = read.get(i);

            result.add(
                    new Todo(
                            Integer.parseInt(row[0]),
                            row[2],
                            ZonedDateTime.of(LocalDateTime.parse(
                                            row[4],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                                    ZoneId.of("Asia/Seoul")),
                            ZonedDateTime.of(LocalDateTime.parse(row[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), ZoneId.of("Asia/Seoul"))
                    ,row[3])
            );
        }

        return result;
    }

    private boolean skipHeader(int i) {
        return i == 0;
    }

//    private List<String[]> readAll(String path) throws IOException {
//        InputStream in = getClass().getResourceAsStream(path);
//        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
//
//        CSVReader csvReader = new CSVReader(reader);
//        return csvReader.readAll();
//    }
}
