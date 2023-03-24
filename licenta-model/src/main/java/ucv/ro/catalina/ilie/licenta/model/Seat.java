package ucv.ro.catalina.ilie.licenta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Seat {
    @Id
    @GeneratedValue
    private int id;
    private int seatRow;
    private int seatNumber;
    @ManyToOne
    private Hall hall;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int row) {
        this.seatRow = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int number) {
        this.seatNumber = number;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
