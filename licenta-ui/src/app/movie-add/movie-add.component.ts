import {Component} from '@angular/core';
import {Movie} from "../model/movie";
import {MovieService} from "../service/movie-service";

@Component({
  selector: 'app-movie-add',
  templateUrl: './movie-add.component.html',
  styleUrls: ['./movie-add.component.css']
})
export class MovieAddComponent {
  movie: Movie = {
    ageRestricted: "", description: "", duration: undefined, genre: "", rating: undefined,
    name: ''
  };
  submitted = false;

  constructor(public movieService: MovieService) {
  }

  saveMovie(): void {
    const data = {
      name: this.movie.name,
      description: this.movie.description,
      rating: this.movie.rating,
      genre: this.movie.genre,
      ageRestricted: this.movie.ageRestricted,
      duration: this.movie.duration,
    };
    this.movieService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newMovie(): void {
    this.submitted = false;
    this.movie = {
      ageRestricted: "", description: "", duration: undefined, genre: "", rating: undefined,
      name: ''
    };
  }
}
