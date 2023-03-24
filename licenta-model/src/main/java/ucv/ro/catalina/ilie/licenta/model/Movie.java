package ucv.ro.catalina.ilie.licenta.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.util.List;
import java.util.Set;

@Entity
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    private Duration duration;
    private int rating;
    @OneToMany
    private Set<Comment> comments;
    @OneToMany(mappedBy = "movie")
    private Set<MovieActor> principalActors;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
