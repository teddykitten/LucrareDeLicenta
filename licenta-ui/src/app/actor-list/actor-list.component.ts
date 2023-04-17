import {Component, OnInit} from '@angular/core';

import {Actor} from '../model/actor';
import {ActorService} from "../service/actor-service";

@Component({
  selector: 'actor-list',
  templateUrl: './actor-list.component.html',
  styleUrls: ['./actor-list.component.css']
})
export class ActorListComponent implements OnInit {

  actors: Actor[];

  constructor(private actorService: ActorService) {
  }

  ngOnInit() {
    this.actorService.findAll().subscribe(data => this.actors = data);
  }
}
