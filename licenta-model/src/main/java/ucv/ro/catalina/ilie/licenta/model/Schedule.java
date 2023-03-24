package ucv.ro.catalina.ilie.licenta.model;

import java.time.LocalDateTime;


public class Schedule {
    private LocalDateTime date;
    private Hall hall;
    private Movie movie;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
