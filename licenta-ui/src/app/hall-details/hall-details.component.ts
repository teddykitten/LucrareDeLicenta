import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Hall} from "../model/hall";
import {HallService} from "../_service/hall-service";

@Component({
  selector: 'app-hall-details',
  templateUrl: './hall-details.component.html',
  styleUrls: ['./hall-details.component.css']
})
export class HallDetailsComponent implements OnInit {
  @Input() viewMode = false;

  @Input() currentHall: Hall = {
    name: '',
  };

  message = '';

  constructor(
    private hallService: HallService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getHall(this.route.snapshot.params["id"]);
    }
  }

  goBack() {
    window.history.back();
  }

  getHall(id: string): void {
    this.hallService.get(id)
      .subscribe({
        next: (data) => {
          this.currentHall = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  deleteHall(): void {
    this.hallService.delete(this.currentHall.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/hall']).then(() => res.message ? res.message : 'This hall was deleted successfully!');
        },
        error: (e) => console.error(e)
      });
  }
}
