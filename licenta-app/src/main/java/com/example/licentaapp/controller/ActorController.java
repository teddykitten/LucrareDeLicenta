package com.example.licentaapp.controller;

import com.example.licentaapp.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucv.ro.catalina.ilie.licenta.model.Actor;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(path = "/actor") //This means URL's start with /demo (after Application path)
public class ActorController {
    @Autowired
    private ActorRepository actorRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewActor(@RequestParam String name) {
        Actor actor = new Actor();
        actor.setName(name);
        actorRepository.save(actor);
        return "Saved";
    }


    @DeleteMapping(path = "/remove/{id}")
    public Map<String, Boolean> deleteActor(@PathVariable(value = "id") int id) {
        actorRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Actor> getAllActors() {
        return actorRepository.findAll();
    }
}
