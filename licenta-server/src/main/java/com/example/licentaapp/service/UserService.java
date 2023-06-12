package com.example.licentaapp.service;

import com.example.licentaapp.repository.RoleRepository;
import com.example.licentaapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ucv.ro.catalina.ilie.licenta.model.Role;
import ucv.ro.catalina.ilie.licenta.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user) {
        Role role = roleRepository.findById("User").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setPassword(getEncodedPassword(user.getPassword()));
        return userRepository.save(user);

    }

    @Autowired
    private RoleRepository roleRepository;

    public void initRolesAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleRepository.save(userRole);

        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setEmail("admin@gmail.com");
        adminUser.setPassword(getEncodedPassword("adminpass"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);

        User user = new User();
        user.setUsername("user");
        user.setEmail("user@gmail.com");
        user.setPassword(getEncodedPassword("userpass"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userRepository.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);

    }
}
