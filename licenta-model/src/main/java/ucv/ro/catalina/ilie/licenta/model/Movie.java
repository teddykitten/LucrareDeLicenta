package ucv.ro.catalina.ilie.licenta.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.util.Set;

@Entity
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private int rating;
    private String genre;
    private String ageRestricted;

    private int duration;
    @OneToMany(mappedBy = "movie")
    private Set<MovieActor> actors;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAgeRestricted() {
        return ageRestricted;
    }

    public void setAgeRestricted(String ageRestricted) {
        this.ageRestricted = ageRestricted;
    }

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

    public Set<MovieActor> getActors() {
        return actors;
    }

    public void setActors(Set<MovieActor> actors) {
        this.actors = actors;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
