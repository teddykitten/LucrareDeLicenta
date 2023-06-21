package com.example.licentaapp.controller;

import com.example.licentaapp.repository.HallRepository;
import com.example.licentaapp.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Hall;
import ucv.ro.catalina.ilie.licenta.model.Seat;

import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping(path = "/cinema")
public class HallController {
    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private SeatRepository seatRepository;

    @PostMapping(path = "/hall")
    public ResponseEntity<Hall> createHall(@RequestBody Hall hall) {
        try {
            Hall hall1 = hallRepository
                    .save(hall);
            return new ResponseEntity<>(hall1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/insertDummyHall")
    public ResponseEntity<Hall> createHall() {
        Hall hall = new Hall();
        hall.setName("test");
        Set<Seat> seats = new HashSet<>();
        for (int r = 1; r <= 2; r++) {
            for (int s = 1; s <= 3; s++) {
                seats.add(new Seat(r, s));
            }
        }
        Set<Seat> collect = seats.stream().map(seat -> seatRepository.save(seat)).collect(Collectors.toSet());
        hall.setSeats(collect);
        Hall hall1 = hallRepository.save(hall);
        return new ResponseEntity<>(hall1, HttpStatus.CREATED);
    }

    @GetMapping(path = "/insertDummyHall/{id}")
    public ResponseEntity<Map<String, Object>> listHall(@PathVariable("id") long id) {
        Hall hall = hallRepository.getReferenceById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("name", hall.getName());
        response.put("rows", hall.getSeats().stream().map(Seat::getSeatRow).distinct().count());
        Map<Integer, Long> collect1 = hall.getSeats().stream().collect(Collectors.groupingBy(Seat::getSeatRow,
                Collectors.counting()));
        response.put("seats", collect1);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/hall/{id}")
    public ResponseEntity<HttpStatus> deleteHall(@PathVariable("id") long id) {
        try {
            hallRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/hall/{id}")
    public ResponseEntity<Hall> getHallById(@PathVariable("id") long id) {
        Optional<Hall> hallData = hallRepository.findById(id);
        return hallData.map(hall -> new ResponseEntity<>(hall, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/hall")
    public ResponseEntity<List<Hall>> getAllHalls(@RequestParam(required = false) String name) {
        try {
            List<Hall> halls = new ArrayList<>();

            if (name == null)
                halls.addAll(hallRepository.findAll());
            else
                halls.addAll(hallRepository.findByName(name));

            if (halls.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(halls, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
