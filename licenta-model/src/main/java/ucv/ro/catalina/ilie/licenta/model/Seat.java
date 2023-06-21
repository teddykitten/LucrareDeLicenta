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

    public Seat(){

    }
    public Seat(int seatRow, int seatNumber) {
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
    }

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
}
