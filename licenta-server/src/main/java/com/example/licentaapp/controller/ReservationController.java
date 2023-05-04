package com.example.licentaapp.controller;

import com.example.licentaapp.repository.HallRepository;
import com.example.licentaapp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Reservation;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/cinema/reservation")
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        try {
            Reservation reservation1 = reservationRepository
                    .save(reservation);
            return new ResponseEntity<>(reservation1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/remove/{id}")
    public Map<String, Boolean> deleteReservation(@PathVariable(value = "id") int id) {
        reservationRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}