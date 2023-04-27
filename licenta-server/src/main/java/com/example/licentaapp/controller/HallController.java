package com.example.licentaapp.controller;

import com.example.licentaapp.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Hall;
import ucv.ro.catalina.ilie.licenta.model.Seat;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(path = "/movie/hall")
public class HallController {
    @Autowired
    private HallRepository hallRepository;
    @GetMapping(path = "/add")
    public @ResponseBody String addNewHall(@RequestParam String name, @RequestParam Set<Seat> seats) {
        Hall hall = new Hall();
        hall.setName(name);
        hall.setSeats(seats);
        hallRepository.save(hall);
        return "Saved";
    }
    @DeleteMapping(path = "/remove/{id}")
    public Map<String, Boolean> deleteHall(@PathVariable(value = "id") int id) {
        hallRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Hall> getAllHalls() {
        return hallRepository.findAll();
    }
}