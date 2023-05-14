package com.example.licentaapp.controller;

import com.example.licentaapp.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Hall;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(path = "/cinema")
public class HallController {
    @Autowired
    private HallRepository hallRepository;

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

    @DeleteMapping("/hall/{id}")
    public ResponseEntity<HttpStatus> deleteHall(@PathVariable("id") long id) {
        try {
            hallRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/hall/{id}")
    public ResponseEntity<Hall> updateHall(@PathVariable("id") long id, @RequestBody Hall hall) {
        Optional<Hall> hallData = hallRepository.findById(id);

        if (hallData.isPresent()) {
            Hall hall1 = hallData.get();
            hall1.setName(hall.getName());
            return new ResponseEntity<>(hallRepository.save(hall1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/hall/{id}")
    public ResponseEntity<Hall> getHallById(@PathVariable("id") long id) {
        Optional<Hall> hallData = hallRepository.findById(id);
        return hallData.map(hall -> new ResponseEntity<>(hall, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/hall")
    public ResponseEntity<List<Hall>> getAllActors(@RequestParam(required = false) String name) {
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
