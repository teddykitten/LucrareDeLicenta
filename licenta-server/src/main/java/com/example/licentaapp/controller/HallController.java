package com.example.licentaapp.controller;

import com.example.licentaapp.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Hall;

@Controller
@RequestMapping(path = "/cinema/hall")
public class HallController {
    @Autowired
    private HallRepository hallRepository;
    @PostMapping(path = "/add")
    public ResponseEntity<Hall> createHall(@RequestBody Hall hall) {
        try {
            Hall hall1 = hallRepository
                    .save(hall);
            return new ResponseEntity<>(hall1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(path = "/remove/{id}")
    public String deleteHall(@PathVariable int id) {
        hallRepository.deleteById(id);
        return "Hall deleted";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Hall> getAllHalls() {
        return hallRepository.findAll();
    }
}