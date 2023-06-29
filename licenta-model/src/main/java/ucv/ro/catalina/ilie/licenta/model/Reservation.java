package ucv.ro.catalina.ilie.licenta.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Schedule schedule;
    private String email;
    @OneToMany
    private List<SeatReserved> seatReserved;

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SeatReserved> getSeatReserved() {
        return seatReserved;
    }

    public void setSeatReserved(List<SeatReserved> seatReserved) {
        this.seatReserved = seatReserved;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
