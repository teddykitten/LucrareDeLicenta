package ucv.ro.catalina.ilie.licenta.model;


public class JwtRequest {
    private String username;
    private String password;

    public String getUserName() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
