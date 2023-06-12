package com.example.licentaapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ucv.ro.catalina.ilie.licenta.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

}
