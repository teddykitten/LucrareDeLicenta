import {Component, Input, OnInit} from "@angular/core";
import {Schedule} from "../model/schedule";
import {Hall} from "../model/hall";
import {Movie} from "../model/movie";
import {ScheduleService} from "../_service/schedule-service";
import {ActivatedRoute, Router} from "@angular/router";
import {ExtendedSchedule} from "../model/extended-schedule";
import {Reservation} from "../model/reservation";
import {ReservationService} from "../_service/reservation-service";
import {Seat} from "../model/seat";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {
  @Input() viewMode = false;
  @Input() currentHall: Hall = {
    rows: [],
    name: ''
  };

  extendedSchedule?: ExtendedSchedule[] = [];
  reservation: Reservation = {
    seats: [],
    email: "",
    schedule: new Schedule
  };
  currentExtendedSchedule: ExtendedSchedule = {
    hallRequestBody: new Hall, schedule: new Schedule, seats: []
  };
  currentIndex = -1;
  message = '';
  submitted = false;

  constructor(
    private scheduleService: ScheduleService,
    private route: ActivatedRoute,
    private reservationSerevice: ReservationService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.retrieveSchedule();
  }

  retrieveSchedule(): void {
    this.scheduleService.findAllExtended()
      .subscribe({
        next: (data) => {
          this.extendedSchedule = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  setActiveSchedule(extendedSchedule: ExtendedSchedule, index: number): void {
    this.currentExtendedSchedule = extendedSchedule;
    this.currentIndex = index;
    console.log(this.currentExtendedSchedule.seats)
    console.log("active schedule", this.currentExtendedSchedule)
  }

  onSubmit(): void {
    const data = {
      email: this.reservation.email,
      scheduleId: this.currentExtendedSchedule.schedule.id,
      seats: this.reservation.seats,
    }
    console.log(data)
    this.reservationSerevice.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }
}
