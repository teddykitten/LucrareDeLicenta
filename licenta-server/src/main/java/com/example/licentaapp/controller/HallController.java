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

import static com.example.licentaapp.controller.HallRequestBody.toHallRequestBody;


@Controller
@RequestMapping(path = "/cinema")
public class HallController {
    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private SeatRepository seatRepository;


    @PostMapping(path = "/hall")
    public ResponseEntity<Hall> createHall(@RequestBody HallRequestBody hallRequestBody) {
        Hall hall = new Hall();
        Set<Seat> seats = new HashSet<>();
        hall.setName(hallRequestBody.getName());
        for (int r = 1; r <= hallRequestBody.getRows().size(); r++) {
            for (int s = 1; s <= hallRequestBody.getRows().get(r - 1).getSeat(); s++) {
                seats.add(new Seat(r, s));
            }
        }
        Set<Seat> collect = seats.stream().map(seat -> seatRepository.save(seat)).collect(Collectors.toSet());
        hall.setSeats(collect);
        Hall hallResult = hallRepository.save(hall);
        return new ResponseEntity<>(hallResult, HttpStatus.CREATED);
    }

    @GetMapping(path = "/hall/{id}")
    public ResponseEntity<HallRequestBody> listHall(@PathVariable("id") long id) {
        Hall hall = hallRepository.getReferenceById(id);
        HallRequestBody hallRequestBody = toHallRequestBody(hall);
        return new ResponseEntity<>(hallRequestBody, HttpStatus.CREATED);
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


    @GetMapping("/hall")
    public ResponseEntity<List<HallRequestBody>> getAllHalls(@RequestParam(required = false) String name) {
        try {
            List<Hall> halls = new ArrayList<>();

            if (name == null)
                halls.addAll(hallRepository.findAll());
            else
                halls.addAll(hallRepository.findByName(name));

            if (halls.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(halls.stream().map(hall -> toHallRequestBody(hall)).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
