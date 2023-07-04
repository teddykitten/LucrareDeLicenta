package ucv.ro.catalina.ilie.licenta.model;

import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Actor {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "imagine",
            joinColumns = {
                    @JoinColumn(name = "actor_id")}, inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private Set<Image> imagine;

    public Set<Image> getActorImage() {
        return imagine;
    }

    public void setActorImage(Set<Image> imagine) {
        this.imagine = imagine;
    }

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
