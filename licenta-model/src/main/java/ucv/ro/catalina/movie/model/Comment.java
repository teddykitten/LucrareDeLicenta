package ucv.ro.catalina.movie.model;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private User user; //(?)
    private String content;
    private Film movie;
    private LocalDateTime postTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
