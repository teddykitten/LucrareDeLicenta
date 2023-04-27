package com.example.licentaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucv.ro.catalina.ilie.licenta.model.Reservation;

public interface ReservationRepository  extends JpaRepository<Reservation, Integer> {

}
