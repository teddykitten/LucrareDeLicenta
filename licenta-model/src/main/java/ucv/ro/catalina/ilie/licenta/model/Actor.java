package ucv.ro.catalina.ilie.licenta.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Actor {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "actor")
    private Set<MovieActor> principalActors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
