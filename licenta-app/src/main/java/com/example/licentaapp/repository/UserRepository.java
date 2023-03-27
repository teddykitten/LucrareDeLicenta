package com.example.licentaapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ucv.ro.catalina.ilie.licenta.model.MyUser;


public interface UserRepository extends CrudRepository<MyUser, Integer> {
     @Query("SELECT u FROM MyUser u where u.username = :username")
     MyUser getUserByUsername(@Param("username") String username);
}
