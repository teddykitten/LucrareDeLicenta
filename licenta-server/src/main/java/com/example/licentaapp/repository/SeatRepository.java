package com.example.licentaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ucv.ro.catalina.ilie.licenta.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}


