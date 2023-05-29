import {Component, Input, OnInit} from '@angular/core';
import {Movie} from "../model/movie";
import {MovieService} from "../service/movie-service";
import {ActivatedRoute, Router} from "@angular/router";
@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {
  @Input() viewMode = false;

  @Input() currentMovie: Movie = {
    ageRestricted: "", description: "", duration: undefined, genre: "", rating: undefined,
    name: ''
  };
  goBack() {
    window.history.back();
  }
  message = '';

  constructor(
    private movieService: MovieService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getMovie(this.route.snapshot.params["id"]);
    }
  }

  getMovie(id: string): void {
    this.movieService.get(id)
      .subscribe({
        next: (data) => {
          this.currentMovie = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updateMovie(): void {
    this.message = '';

    this.movieService.update(this.currentMovie.id, this.currentMovie)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This movie was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  deleteMovie(): void {
    this.movieService.delete(this.currentMovie.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/movie']).then(() => res.message ? res.message : 'This movie was deleted successfully!');
        },
        error: (e) => console.error(e)
      });
  }

}
