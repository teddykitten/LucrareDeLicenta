package com.example.licentaapp.controller;

import com.example.licentaapp.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Actor;


@Controller
@RequestMapping(path = "/cinema/actor")
public class ActorController {
    @Autowired
    private ActorRepository actorRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        try {
            Actor _actor = actorRepository
                    .save(actor);
            return new ResponseEntity<>(_actor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/remove/{id}")
    public String deleteActor(@PathVariable int id) {
        actorRepository.deleteById(id);
        return "Actor deleted";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Actor> getAllActors() {
        return actorRepository.findAll();
    }
}
