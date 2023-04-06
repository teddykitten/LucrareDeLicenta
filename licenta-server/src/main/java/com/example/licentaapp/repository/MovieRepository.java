package com.example.licentaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucv.ro.catalina.ilie.licenta.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
