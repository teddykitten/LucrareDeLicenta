package ucv.ro.catalina.movie.model;

public class MovieActor extends Actor {
    private boolean principalRole;

    public boolean isPrincipalRole() {
        return principalRole;
    }

    public void setPrincipalRole(boolean principalRole) {
        this.principalRole = principalRole;
    }
}
