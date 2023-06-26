import {Component, OnInit} from '@angular/core';
import {UserService} from "../_service/user.service";
import {Hall} from "../model/hall";
import {HallService} from "../_service/hall-service";

@Component({
  selector: 'app-movie-list',
  templateUrl: './hall-list.component.html',
  styleUrls: ['./hall-list.component.css']
})
export class HallListComponent implements OnInit {

  halls?: Hall[];
  currentHall: Hall = {
    rows: [],
    name: ""

  };
  currentIndex = -1;
  name = '';

  constructor(private hallService: HallService,
              public userService: UserService) {
  }

  ngOnInit(): void {
    this.retrieveHall();
  }

  retrieveHall(): void {
    this.hallService.findAll()
      .subscribe({
        next: (data) => {
          this.halls = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrieveHall();
    this.currentHall = {
      rows:[],
      name: ""
    };
    this.currentIndex = -1;
  }

  setActiveHall(hall: Hall, index: number): void {
    console.log(hall)
    this.currentHall = hall;
    this.currentIndex = index;
  }

  searchName(): void {
    this.currentHall = {
      rows: [],
      name: ""
    };
    this.currentIndex = -1;

    this.hallService.findByName(this.name)
      .subscribe({
        next: (data) => {
          this.halls = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}
