package ucv.ro.catalina.ilie.licenta.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            })
    private Set<Role> role;
    @OneToMany
    private Set<Movie> bookedMovies;
    @OneToMany
    private Set<Comment> comments;

    public Set<Movie> getBookedMovies() {
        return bookedMovies;
    }

    public void setBookedMovies(Set<Movie> bookedMovies) {
        this.bookedMovies = bookedMovies;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Movie> getBookedFilms() {
        return bookedMovies;
    }

    public void setBookedFilms(Set<Movie> bookedMovies) {
        this.bookedMovies = bookedMovies;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
