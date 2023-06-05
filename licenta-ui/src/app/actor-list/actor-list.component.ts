import {Component, OnInit} from '@angular/core';
import {ActorService} from "../service/actor-service";
import {Actor} from "../model/actor";

@Component({
  selector: 'app-actor-list',
  templateUrl: './actor-list.component.html',
  styleUrls: ['./actor-list.component.css']
})
export class ActorListComponent implements OnInit {

  actors?: Actor[];
  currentActor: Actor = {name: "", imagine: []};
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
    this.currentActor = {name: "", imagine: []};
    this.currentIndex = -1;
  }

  setActiveActor(actor: Actor, index: number): void {
    this.currentActor = actor;
    this.currentIndex = index;
  }

  searchName(): void {
    this.currentActor = {name: "", imagine: []};
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
