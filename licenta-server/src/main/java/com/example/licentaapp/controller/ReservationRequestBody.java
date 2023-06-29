package com.example.licentaapp.controller;

import ucv.ro.catalina.ilie.licenta.model.Seat;

import java.util.List;

public class ReservationRequestBody {
    private long scheduleId;
    private String email;
    private List<List<String>> seats;

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<List<String>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<String>> seatReserved) {
        this.seats = seatReserved;
    }
}
