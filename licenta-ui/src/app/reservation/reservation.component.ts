import {Component, Input, OnInit} from "@angular/core";
import {Schedule} from "../model/schedule";
import {Hall} from "../model/hall";
import {Movie} from "../model/movie";
import {ScheduleService} from "../_service/schedule-service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit{
  @Input() viewMode = false;
  @Input() currentHall: Hall = {
    rows: [],
    name: ''
  };
  schedules?: Schedule[] = [];
  currentSchedule: Schedule = {hall: new Hall, movie: new Movie, date: undefined};
  currentIndex = -1;
  message = '';

  constructor(
    private scheduleService: ScheduleService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit(): void {
    this.retrieveSchedule();
  }
  retrieveSchedule(): void {
    console.log(this.schedules)
    this.scheduleService.findAll()
      .subscribe({
        next: (data) => {
          this.schedules = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
  setActiveSchedule(schedule: Schedule, index: number): void {
    this.currentSchedule = schedule;
    this.currentIndex = index;
    console.log("active schedule", this.currentSchedule)
  }

}
