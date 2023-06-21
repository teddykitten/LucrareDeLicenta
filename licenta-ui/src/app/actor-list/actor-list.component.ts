import {Component, OnInit} from '@angular/core';
import {ActorService} from ".././_service/actor-service";
import {Actor} from "../model/actor";
import {map} from "rxjs/operators";
import {ImageProcessingService} from ".././_service/image-processing.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ActorDetailsComponent} from "../actor-details/actor-details.component";
import {UserService} from "../_service/user.service";

@Component({
  selector: 'app-actor-list',
  templateUrl: './actor-list.component.html',
  styleUrls: ['./actor-list.component.css']
})
export class ActorListComponent implements OnInit {

  actors?: Actor[] = [];
  currentActor: Actor = {name: "", actorImage: []};
  currentIndex = -1;
  name = '';

  constructor(private actorService: ActorService,
              private imageProcessingService: ImageProcessingService,
              public userService: UserService) {

  }

  ngOnInit(): void {
    this.retrieveActor();
  }

  retrieveActor(): void {
    this.actorService.findAll()
      .pipe(
        map((x: Actor[], index) => x.map((actor: Actor) => this.imageProcessingService.createImages(actor)))
      )
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
    this.currentActor = {name: "", actorImage: []};
    this.currentIndex = -1;
  }

  setActiveActor(actor: Actor, index: number): void {
    this.currentActor = actor;
    this.currentIndex = index;
    console.log("active actor", this.currentActor)
  }

  searchName(): void {
    this.currentActor = {name: "", actorImage: []};
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
