package com.example.licentaapp.controller;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import ucv.ro.catalina.ilie.licenta.model.Hall;
import ucv.ro.catalina.ilie.licenta.model.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HallRequestBody {
    static class Row {
        private int seat;

        Row(){

        }

        public int getSeat() {
            return seat;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }
    }

    private int id;
    private String name;
    private List<Row> rows;
    HallRequestBody(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static HallRequestBody toHallRequestBody(Hall hall) {
        HallRequestBody hallRequestBody = new HallRequestBody();
        hallRequestBody.setName(hall.getName());
        hallRequestBody.setId(hall.getId());
        List<HallRequestBody.Row> rows = new ArrayList<>();
        Map<Integer, Long> seats = hall.getSeats().stream().collect(Collectors.groupingBy(Seat::getSeatRow,
                Collectors.counting()));
        for (int r = 1; r <= hall.getSeats().stream().map(Seat::getSeatRow).distinct().count(); r++) {
            HallRequestBody.Row row = new HallRequestBody.Row();
            row.setSeat(seats.get(r).intValue());
            rows.add(row);
        }
        hallRequestBody.setRows(rows);
        return hallRequestBody;
    }
}
