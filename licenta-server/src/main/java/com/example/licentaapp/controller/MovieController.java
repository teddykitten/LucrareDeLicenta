package com.example.licentaapp.controller;

import com.example.licentaapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(path = "/cinema")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @PostMapping(path = "/movie-add")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        try {
            Movie movie1 = movieRepository
                    .save(movie);
            return new ResponseEntity<>(movie1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("id") long id) {
        try {
            movieRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") long id, @RequestBody Movie movie) {
        Optional<Movie> movieData = movieRepository.findById(id);

        if (movieData.isPresent()) {
            Movie movie1 = movieData.get();
            movie1.setName(movie.getName());
            return new ResponseEntity<>(movieRepository.save(movie1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") long id) {
        Optional<Movie> movieData = movieRepository.findById(id);
        return movieData.map(movie -> new ResponseEntity<>(movie, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(required = false) String name, @RequestParam(required = false) String genre) {
        try {
            List<Movie> movies = new ArrayList<>();

            if (name == null)
                movies.addAll(movieRepository.findAll());
            else
                movies.addAll(movieRepository.findByName(name));
            if (genre == null)
                movies.addAll(movieRepository.findAll());
            else {
                movies.addAll(movieRepository.findByGenre(genre));
            }
            if (movies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
