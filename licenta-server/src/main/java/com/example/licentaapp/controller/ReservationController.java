package com.example.licentaapp.controller;

import com.example.licentaapp.repository.ReservationRepository;
import com.example.licentaapp.repository.ScheduleRepository;
import com.example.licentaapp.repository.SeatRepository;
import com.example.licentaapp.repository.SeatReservedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Reservation;
import ucv.ro.catalina.ilie.licenta.model.Schedule;
import ucv.ro.catalina.ilie.licenta.model.Seat;
import ucv.ro.catalina.ilie.licenta.model.SeatReserved;

import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping(path = "/cinema")
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private SeatReservedRepository seatReservedRepository;
    @Autowired
    private JavaMailSender emailSender;


    @PostMapping(path = "/reservation")
    public ResponseEntity<Boolean> createReservation(@RequestBody ReservationRequestBody reservationRequestBody) {
        List<Seat> seatsReserved = reservationRequestBody.getSeats().stream()
                .filter(Objects::nonNull)
                .filter(list -> !list.isEmpty())
                .flatMap(seats -> seats.stream().map(s -> s.split(","))
                        .map(strings -> new Seat(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]))))
                .collect(Collectors.toList());
        Reservation reservation = new Reservation();
        Schedule schedule = scheduleRepository.findById(reservationRequestBody.getScheduleId()).get();
        Set<Seat> hallSeats = schedule.getHall().getSeats();
        reservation.setSchedule(schedule);
        reservation.setEmail(reservationRequestBody.getEmail());
        reservation.setSeatReserved(seatsReserved.stream().map(seat -> {
            SeatReserved seatReserved = new SeatReserved();
            seatReserved.setSchedule(schedule);
            seatReserved.setSeat(hallSeats.stream().filter(s -> s.getSeatRow() == seat.getSeatRow() && s.getSeatNumber() == seat.getSeatNumber())
                    .findFirst().get());
            return seatReserved;
        }).collect(Collectors.toList()));
        reservation.getSeatReserved().forEach(seatReservedRepository::save);
        reservationRepository.save(reservation);
        sendSimpleMessage(reservation.getEmail(), "Rezervare confirmata", "Salut, ai rezervat " + seatsReserved.size()
        + " locuri la filmul " + schedule.getMovie().getName() + " in data de " + schedule.getDate() + " in sala " + schedule.getHall().getName() );
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") long id) {
        Optional<Reservation> reservationData = reservationRepository.findById(id);
        return reservationData.map(reservation -> new ResponseEntity<>(reservation, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/reservation")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        try {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@cinemacata.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
