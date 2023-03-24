package ucv.ro.catalina.ilie.licenta.model;

import jakarta.persistence.*;

@Entity
public class MovieActor {
    @EmbeddedId
    private MovieActorKey id;

    @ManyToOne
    @MapsId("actorId")
    private Actor actor;
    @ManyToOne
    @MapsId("movieId")
    private Movie movie;
    private boolean principal;

    public MovieActor(MovieActorKey id, Actor actor, Movie movie, boolean principal) {
        this.id = id;
        this.actor = actor;
        this.movie = movie;
        this.principal = principal;
    }

    public MovieActor() {

    }

    public MovieActorKey getId() {
        return id;
    }

    public void setId(MovieActorKey id) {
        this.id = id;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

}
