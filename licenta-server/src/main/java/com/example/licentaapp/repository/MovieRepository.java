package com.example.licentaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucv.ro.catalina.ilie.licenta.model.Hall;
import ucv.ro.catalina.ilie.licenta.model.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByName(String name);

    List<Movie> findByGenre(String genre);

}
