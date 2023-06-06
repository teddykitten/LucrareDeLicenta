package ucv.ro.catalina.ilie.licenta.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Actor {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "actor_image",
            joinColumns = {
                    @JoinColumn(name = "actor_id")}, inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private Set<Image> actorImage;

    public Set<Image> getActorImage() {
        return actorImage;
    }

    public void setActorImage(Set<Image> actorImage) {
        this.actorImage = actorImage;
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
