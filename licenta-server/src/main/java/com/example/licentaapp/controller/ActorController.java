package com.example.licentaapp.controller;

import com.example.licentaapp.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ucv.ro.catalina.ilie.licenta.model.Actor;
import ucv.ro.catalina.ilie.licenta.model.Image;

import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping(path = "/cinema")
public class ActorController {
    @Autowired
    private ActorRepository actorRepository;

    @PostMapping(path = "/actor", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Actor> createActor(@RequestPart("actor") Actor actor,
                                             @RequestPart("imageFile") MultipartFile[] file) {
        try {
            Actor _actor = actorRepository
                    .save(actor);
            Set<Image> images = uploadImage(file);
            actor.setActorImage(images);
            return new ResponseEntity<>(_actor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Set<Image> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<Image> images = new HashSet<>();
        for (MultipartFile file : multipartFiles) {
            Image image = new Image(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            images.add(image);
        }
        return images;
    }

    @DeleteMapping("/actor/{id}")
    public ResponseEntity<HttpStatus> deleteActor(@PathVariable("id") long id) {
        try {
            actorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actor/{id}")
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

    @GetMapping("/actor/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") long id) {
        Optional<Actor> actorData = actorRepository.findById(id);
        return actorData.map(actor -> new ResponseEntity<>(actor, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/actor")
    public ResponseEntity<List<Actor>> getAllActors(@RequestParam(required = false) String name) {
        try {
            List<Actor> actors = new ArrayList<>();

            if (name == null)
                actors.addAll(actorRepository.findAll());
            else
                actors.addAll(actorRepository.findByName(name));

            if (actors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(actors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
