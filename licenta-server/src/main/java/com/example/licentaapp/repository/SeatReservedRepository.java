package com.example.licentaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ucv.ro.catalina.ilie.licenta.model.SeatReserved;

public interface SeatReservedRepository extends JpaRepository<SeatReserved, Long> {
}
