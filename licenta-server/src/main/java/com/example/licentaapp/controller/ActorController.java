package com.example.licentaapp.controller;

import com.example.licentaapp.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Actor;

import java.util.Optional;


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

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deleteActor(@PathVariable("id") long id) {
        try {
            actorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable("id") long id, @RequestBody Actor actor) {
        Optional<Actor> actorData = actorRepository.findById(id);

        if (actorData.isPresent()) {
            Actor actor1 = actorData.get();
            actor1.setName(actor.getName());
            return new ResponseEntity<>(actorRepository.save(actor1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") long id) {
        Optional<Actor> actorData = actorRepository.findById(id);
        return actorData.map(actor -> new ResponseEntity<>(actor, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Actor> getAllActors() {
        return actorRepository.findAll();
    }
}
