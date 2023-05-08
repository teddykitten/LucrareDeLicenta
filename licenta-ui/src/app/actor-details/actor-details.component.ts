import {Component, Input, OnInit} from '@angular/core';
import {Actor} from "../model/actor";
import {ActivatedRoute, Router} from "@angular/router";
import {ActorService} from "../service/actor-service";

@Component({
  selector: 'app-actor-details',
  templateUrl: './actor-details.component.html',
  styleUrls: ['./actor-details.component.css']
})
export class ActorDetailsComponent implements OnInit {
  @Input() viewMode = false;

  @Input() currentActor: Actor = {
    name: ''
  };

  message = '';

  constructor(
    private actorService: ActorService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getActor(this.route.snapshot.params["id"]);
    }
  }

  getActor(id: string): void {
    this.actorService.get(id)
      .subscribe({
        next: (data) => {
          this.currentActor = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updateActor(): void {
    this.message = '';

    this.actorService.update(this.currentActor.id, this.currentActor)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This actor was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  deleteActor(): void {
    this.actorService.delete(this.currentActor.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/cinema/actor']).then(() =>res.message ? res.message : 'This actor was deleted successfully!' );
        },
        error: (e) => console.error(e)
      });
  }
}
