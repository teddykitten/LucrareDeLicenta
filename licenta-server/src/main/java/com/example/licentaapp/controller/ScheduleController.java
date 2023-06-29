package com.example.licentaapp.controller;

import com.example.licentaapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Hall;
import ucv.ro.catalina.ilie.licenta.model.Movie;
import ucv.ro.catalina.ilie.licenta.model.Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.licentaapp.controller.HallRequestBody.toHallRequestBody;

@Controller
@RequestMapping(path = "/cinema")
public class ScheduleController {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private SeatReservedRepository seatReservedRepository;
    @Autowired
    private SeatRepository seatRepository;

    @PostMapping(path = "/schedule")
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleRequestBody scheduleRequestBody) {
        Schedule schedule = new Schedule();
        schedule.setMovie(movieRepository.findById(scheduleRequestBody.getMovie()).get());
        schedule.setHall(hallRepository.findById(scheduleRequestBody.getHall()).get());
        String date = scheduleRequestBody.getDate();
        String time = scheduleRequestBody.getTime();
        LocalDateTime localDateTime = LocalDateTime.parse(date + time, DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm"));
        schedule.setDate(localDateTime);
        Schedule scheduleResult = scheduleRepository.save(schedule);
        return new ResponseEntity<>(scheduleResult, HttpStatus.CREATED);

    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable("id") long id) {
        try {
            scheduleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable("id") long id) {
        Optional<Schedule> scheduleData = scheduleRepository.findById(id);
        return scheduleData.map(schedule -> new ResponseEntity<>(schedule, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        try {
            List<Schedule> schedules = new ArrayList<>(scheduleRepository.findAll());
            if (schedules.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(schedules, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/extendedSchedule")
    public ResponseEntity<List<ExtendedSchedule>> getAllExtendedSchedules() {
        try {
            List<Schedule> schedules = new ArrayList<>(scheduleRepository.findAll());
            if (schedules.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }


            return new ResponseEntity<>(schedules.stream().map(schedule -> new ExtendedSchedule(toHallRequestBody(schedule.getHall()), schedule, getReservedSeats(schedule)))
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<String> getReservedSeats(Schedule schedule) {
        return seatReservedRepository.findAll().stream()
                .filter(seatReserved -> seatReserved.getSchedule().getId() == schedule.getId())
                .map(seatReserved -> seatReserved.getSeat())
                .map(seat -> seat.getSeatRow() + "," + seat.getSeatNumber())
                .collect(Collectors.toList());
    }
}
