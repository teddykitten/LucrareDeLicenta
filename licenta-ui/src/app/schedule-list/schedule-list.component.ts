import {Component, OnInit} from "@angular/core";
import {Actor} from "../model/actor";
import {ActorService} from "../_service/actor-service";
import {ImageProcessingService} from "../_service/image-processing.service";
import {UserService} from "../_service/user.service";
import {map} from "rxjs/operators";
import {Schedule} from "../model/schedule";
import {ScheduleService} from "../_service/schedule-service";
import {Hall} from "../model/hall";
import {Movie} from "../model/movie";

@Component({
  selector: 'app-schedule-list',
  templateUrl: './schedule-list.component.html',
  styleUrls: ['./schedule-list.component.css']
})
export class ScheduleListComponent implements OnInit {

  schedules?: Schedule[] = [];
  currentSchedule: Schedule = {hall: new Hall, movie: new Movie, date: undefined};
  currentIndex = -1;

  constructor(private scheduleService: ScheduleService,
              public userService: UserService) {

  }

  ngOnInit(): void {
    this.retrieveSchedule();
  }

  retrieveSchedule(): void {
    this.scheduleService.findAll()
      .subscribe({
        next: (data) => {
          this.schedules = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrieveSchedule();
    this.currentSchedule = {date: undefined, hall: new Hall, movie: new Movie};
    this.currentIndex = -1;
  }

  setActiveSchedule(schedule: Schedule, index: number): void {
    this.currentSchedule = schedule;
    this.currentIndex = index;
    console.log("active schedule", this.currentSchedule)
  }

}
