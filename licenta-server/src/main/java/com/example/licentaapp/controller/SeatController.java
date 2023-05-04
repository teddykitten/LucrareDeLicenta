package com.example.licentaapp.controller;

import com.example.licentaapp.repository.HallRepository;
import com.example.licentaapp.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Seat;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(path = "/cinema/hall/seat")
public class SeatController {
    @Autowired
    private SeatRepository seatRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat) {
        try {
            Seat seat1 = seatRepository
                    .save(seat);
            return new ResponseEntity<>(seat1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/remove/{id}")
    public Map<String, Boolean> deleteSeat(@PathVariable(value = "id") int id) {
        seatRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Seat> getAllSeats() {
        return seatRepository.findAll();
    }
}