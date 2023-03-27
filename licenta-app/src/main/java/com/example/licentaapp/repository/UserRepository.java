package com.example.licentaapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ucv.ro.catalina.ilie.licenta.model.User;


public interface UserRepository extends CrudRepository<User, Integer> {
     @Query("SELECT u FROM User u where u.username = :username")
     User getUserByUsername(@Param("username") String username);
}
