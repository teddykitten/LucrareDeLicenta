package com.example.licentaapp.controller;

import ucv.ro.catalina.ilie.licenta.model.Schedule;

import java.util.List;

public class ExtendedSchedule{
    private HallRequestBody hallRequestBody;
    private Schedule schedule;
    private List<String> seats;

    public ExtendedSchedule(HallRequestBody hallRequestBody, Schedule schedule, List<String> seats) {
        this.hallRequestBody = hallRequestBody;
        this.schedule = schedule;
        this.seats = seats;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public HallRequestBody getHallRequestBody() {
        return hallRequestBody;
    }

    public void setHallRequestBody(HallRequestBody hallRequestBody) {
        this.hallRequestBody = hallRequestBody;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
