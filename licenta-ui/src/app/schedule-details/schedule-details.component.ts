import {Component, Input, OnInit} from "@angular/core";
import {Actor} from "../model/actor";
import {ActorService} from "../_service/actor-service";
import {ActivatedRoute, Router} from "@angular/router";
import {ImageProcessingService} from "../_service/image-processing.service";
import {Schedule} from "../model/schedule";
import {ScheduleService} from "../_service/schedule-service";
import {Movie} from "../model/movie";
import {Hall} from "../model/hall";

@Component({
  selector: 'app-schedule-details',
  templateUrl: './schedule-details.component.html',
  styleUrls: ['./schedule-details.component.css']
})
export class ScheduleDetailsComponent implements OnInit {
  @Input() viewMode = false;

  @Input() currentSchedule: Schedule = {
    hall: new Hall,
    movie: new Movie,
    date: undefined
  };

  message = '';

  constructor(
    private scheduleService: ScheduleService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getSchedule(this.route.snapshot.params["id"]);
    }
  }

  goBack() {
    window.history.back();
  }

  getSchedule(id: string): void {
    this.scheduleService.get(id)
      .subscribe({
        next: (data) => {
          this.currentSchedule = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  deleteSchedule(): void {
    this.scheduleService.delete(this.currentSchedule.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/schedule']).then(() => res.message ? res.message : 'This Schedule was deleted successfully!');
        },
        error: (e) => console.error(e)
      });
  }

}
