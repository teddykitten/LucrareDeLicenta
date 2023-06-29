package com.example.licentaapp.controller;

import com.example.licentaapp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Reservation;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(path = "/cinema")
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping(path = "/reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        try {
            Reservation reservation1 = reservationRepository
                    .save(reservation);
            return new ResponseEntity<>(reservation1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
}
