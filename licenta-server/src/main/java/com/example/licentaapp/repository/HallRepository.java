package com.example.licentaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucv.ro.catalina.ilie.licenta.model.Hall;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Long> {
    List<Hall> findByName(String name);
}
