import {Component, OnInit} from '@angular/core';
// @ts-ignore
import {Actor} from '../model/actor';
import {ActorServiceService} from "../actor-service.service";

@Component({
  selector: 'app-actor-list',
  templateUrl: './actor-list.component.html',
  styleUrls: ['./actor-list.component.css']
})
export class ActorListComponent implements OnInit {

  actors: Actor[];

  constructor(private actorService: ActorServiceService) {
  }

  ngOnInit() {
    // @ts-ignore
    this.actorService.findAll().subscribe(data => this.actors = data);
  }
}
