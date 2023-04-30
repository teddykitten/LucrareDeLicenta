package com.example.licentaapp.controller;

import com.example.licentaapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public @ResponseBody String addNewMovie(@RequestParam String name, @RequestParam String description, @RequestParam Set<MovieActor> actors, @RequestParam Duration duration, @RequestParam int rating) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDescription(description);
        movie.setActors(actors);
        movie.setDuration(duration);
        movie.setRating(rating);
        movieRepository.save(movie);
        return "Saved";
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

