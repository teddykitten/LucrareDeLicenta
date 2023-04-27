package com.example.licentaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucv.ro.catalina.ilie.licenta.model.Hall;

public interface HallRepository extends JpaRepository<Hall, Integer> {
}
