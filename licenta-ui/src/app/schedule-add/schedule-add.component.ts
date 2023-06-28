import {Component} from "@angular/core";
import {Schedule} from "../model/schedule";
import {Hall} from "../model/hall";
import {Movie} from "../model/movie";
import {ScheduleService} from "../_service/schedule-service";
import {MovieService} from "../_service/movie-service";
import {HallService} from "../_service/hall-service";
import {FormControl, FormGroup, UntypedFormControl} from "@angular/forms";

@Component({
  selector: 'app-schedule-add',
  templateUrl: './schedule-add.component.html',
  styleUrls: ['./schedule-add.component.css']
})
export class ScheduleAddComponent {
  schedule: Schedule = {
    date: undefined, hall: new Hall, movie: new Movie
  }
  movies: Movie[]
  halls: Hall[]
  submitted = false;
  selectedMovie: any;
  selectedHall: any;
  date: UntypedFormControl = new UntypedFormControl();
  time: UntypedFormControl = new UntypedFormControl();
  constructor(public scheduleService: ScheduleService,
              public movieService: MovieService,
              public hallService: HallService) {
    movieService.findAll().subscribe({
      next: (data) => {
        this.movies = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });

    hallService.findAll().subscribe({
      next: (data) => {
        this.halls = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }

  saveSchedule(): void {
    const data = {
      time: this.time.value,
      date: this.date.value.toISOString().substring(0, 10),
      hall: this.selectedHall,
      movie: this.selectedMovie
    };
    console.log(data)
    this.scheduleService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  goBack() {
    window.history.back();
  }

  newSchedule():
    void {
    this.submitted = false;
    this.schedule = {
      date: undefined, hall: new Hall, movie: new Movie
    };
  }


}
