package com.example.licentaapp.controller;

import com.example.licentaapp.repository.HallRepository;
import com.example.licentaapp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Hall;
import ucv.ro.catalina.ilie.licenta.model.Reservation;
import ucv.ro.catalina.ilie.licenta.model.Schedule;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(path = "/cinema/reservation")
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping(path = "/add")
    public @ResponseBody String addNewReservation(@RequestParam Set<Schedule> schedule) {
        Reservation reservation = new Reservation();
        reservation.setSchedule(schedule);
        reservationRepository.save(reservation);
        return "Saved";
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