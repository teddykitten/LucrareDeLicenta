package ucv.ro.catalina.movie.model;

public class Hall {
    private int hallNumber;
    private Seat numberOfSeats;

    public int getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    public Seat getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Seat numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
