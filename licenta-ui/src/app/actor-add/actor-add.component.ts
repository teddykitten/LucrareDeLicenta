import {Component} from '@angular/core';
import {ActorService} from "../service/actor-service";
import {Actor} from "../model/actor";

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

  goBack() {
    window.history.back();
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
