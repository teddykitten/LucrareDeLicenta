package ucv.ro.catalina.movie.model;

public class Hall {
    private int id;

    private String name;

    private Seat numberOfSeats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seat getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Seat numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
