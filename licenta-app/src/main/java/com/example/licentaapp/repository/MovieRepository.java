package com.example.licentaapp.repository;

import org.springframework.data.repository.CrudRepository;
import ucv.ro.catalina.ilie.licenta.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

}
