package com.example.licentaapp.controller;

import com.example.licentaapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Actor;
import ucv.ro.catalina.ilie.licenta.model.Movie;
import ucv.ro.catalina.ilie.licenta.model.MovieActor;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(path = "/cinema/movie")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        try {
            Movie movie1 = movieRepository
                    .save(movie);
            return new ResponseEntity<>(movie1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(path = "/remove/{id}")
    public String deleteMovie(@PathVariable int id) {
        movieRepository.deleteById(id);
        return "Movie deleted";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}

