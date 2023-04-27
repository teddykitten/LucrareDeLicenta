package com.example.licentaapp.controller;

import com.example.licentaapp.repository.HallRepository;
import com.example.licentaapp.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Hall;
import ucv.ro.catalina.ilie.licenta.model.Seat;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(path = "/movie/seat")
public class SeatController {
    @Autowired
    private SeatRepository seatRepository;

    @GetMapping(path = "/add")
    public @ResponseBody String addNewHall(@RequestParam int seatRow, @RequestParam int seatNumber, @RequestParam Hall hall) {
        Seat seat = new Seat();
        seat.setSeatRow(seatRow);
        seat.setSeatNumber(seatNumber);
        seat.setHall(hall);
        seatRepository.save(seat);
        return "Saved";
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