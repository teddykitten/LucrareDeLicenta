import {Component, OnInit} from '@angular/core';
import {Actor} from "../model/actor";
import {ActorService} from "../service/actor-service";

@Component({
  selector: 'app-actor-list',
  templateUrl: './actor-list.component.html',
  styleUrls: ['./actor-list.component.css']
})
export class ActorListComponent implements OnInit {

  actors?: Actor[];
  currentActor: Actor = {name: ""};
  currentIndex = -1;
  name = '';

  constructor(private actorService: ActorService) {
  }

  ngOnInit(): void {
    this.retrieveActor();
  }

  retrieveActor(): void {
    this.actorService.findAll()
      .subscribe({
        next: (data) => {
          this.actors = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrieveActor();
    this.currentActor = {name: ""};
    this.currentIndex = -1;
  }

  setActiveActor(actor: Actor, index: number): void {
    this.currentActor = actor;
    this.currentIndex = index;
  }

  searchName(): void {
    this.currentActor = {name: ""};
    this.currentIndex = -1;

    this.actorService.findByName(this.name)
      .subscribe({
        next: (data) => {
          this.actors = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}
