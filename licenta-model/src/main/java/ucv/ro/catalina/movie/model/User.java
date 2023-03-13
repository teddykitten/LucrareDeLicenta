package ucv.ro.catalina.movie.model;

import java.util.List;

public class User {
    private int id;
    private String username;
    private String email;
    private char[] password;
    private List<Film> bookedFilms;
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Film> getBookedFilms() {
        return bookedFilms;
    }

    public void setBookedFilms(List<Film> bookedFilms) {
        this.bookedFilms = bookedFilms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
