package com.example.licentaapp.controller;

import com.example.licentaapp.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Seat;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(path = "/cinema")
public class SeatController {
    @Autowired
    private SeatRepository seatRepository;

    @PostMapping(path = "/seat")
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat) {
        try {
            Seat seat1 = seatRepository
                    .save(seat);
            return new ResponseEntity<>(seat1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/seat/{id}")
    public ResponseEntity<HttpStatus> deleteSeat(@PathVariable("id") long id) {
        try {
            seatRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/seat/{id}")
    public ResponseEntity<Seat> updateSeat(@PathVariable("id") long id, @RequestBody Seat seat) {
        Optional<Seat> seatData = seatRepository.findById(id);

        if (seatData.isPresent()) {
            Seat seat1 = seatData.get();
            seat1.setSeatNumber(seat.getSeatNumber());
            seat1.setSeatRow(seat.getSeatRow());
            return new ResponseEntity<>(seatRepository.save(seat1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/seat/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable("id") long id) {
        Optional<Seat> seatData = seatRepository.findById(id);
        return seatData.map(seat -> new ResponseEntity<>(seat, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/seat")
    public ResponseEntity<List<Seat>> getAllSeats() {
        try {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
