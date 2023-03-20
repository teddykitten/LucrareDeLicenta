package com.example.licentaapp;


import org.springframework.data.repository.CrudRepository;
import ucv.ro.catalina.ilie.licenta.model.Actor;

public interface ActorRepository extends CrudRepository<Actor, Integer> {
}

