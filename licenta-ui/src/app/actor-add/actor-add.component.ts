import {Component} from '@angular/core';
import {Actor} from "../model/actor";
import {ActorService} from "../service/actor-service";

@Component({
  selector: 'app-actor-add',
  templateUrl: './actor-add.component.html',
  styleUrls: ['./actor-add.component.css']
})
export class ActorAddComponent {
  actor: Actor = {
    name: ''
  };
  submitted = false;

  constructor(public actorService: ActorService) {
  }

  saveActor(): void {
    const data = {
      name: this.actor.name,
    };
    this.actorService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newActor(): void {
    this.submitted = false;
    this.actor = {
      name: ''
    };
  }
}
