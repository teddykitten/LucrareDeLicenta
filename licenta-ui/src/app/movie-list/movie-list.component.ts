import {Component, OnInit} from '@angular/core';
import {Movie} from "../model/movie";
import {MovieService} from "../_service/movie-service";
import {UserService} from "../_service/user.service";

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  movies?: Movie[];
  currentMovie: Movie = {
    ageRestricted: "",
    description: "",
    duration: undefined,
    genre: "",
    rating: undefined,
    name: ""
  };
  currentIndex = -1;
  name = '';

  constructor(private movieService: MovieService,
              public userService: UserService) {
  }

  ngOnInit(): void {
    this.retrieveMovie();
  }

  retrieveMovie(): void {
    this.movieService.findAll()
      .subscribe({
        next: (data) => {
          this.movies = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrieveMovie();
    this.currentMovie = {
      ageRestricted: "",
      description: "",
      duration: undefined,
      genre: "",
      rating: undefined,
      name: ""
    };
    this.currentIndex = -1;
  }

  setActiveMovie(movie: Movie, index: number): void {
    this.currentMovie = movie;
    this.currentIndex = index;
  }

  searchName(): void {
    this.currentMovie = {
      ageRestricted: "",
      description: "",
      duration: undefined,
      genre: "",
      rating: undefined,
      name: ""
    };
    this.currentIndex = -1;

    this.movieService.findByName(this.name)
      .subscribe({
        next: (data) => {
          this.movies = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}
