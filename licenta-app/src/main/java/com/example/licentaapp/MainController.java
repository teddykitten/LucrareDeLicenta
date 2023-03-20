package com.example.licentaapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Actor;


@Controller
@RequestMapping(path = "/demo") //This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired
    private ActorRepository actorRepository;

    @PostMapping(path = " /add")
    public @ResponseBody String addNewActor(@RequestParam String name) {
        Actor actor = new Actor();
        actor.setName(name);
        actorRepository.save(actor);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Actor> getAllActors() {
        return actorRepository.findAll();
    }
}
