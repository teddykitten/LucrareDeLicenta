import {Component, OnInit, ViewChild} from '@angular/core';
import {Movie} from "../model/movie";
import {MovieService} from "../service/movie-service";
import {UntypedFormControl} from "@angular/forms";
import {ReplaySubject, Subject, take, takeUntil} from "rxjs";
import {MatSelect} from "@angular/material/select";

export interface Genre {
  id: string;
  name: string;
}
export const GENRE: Genre[] = [
  { name: 'action', id: 'A' },
  { name: 'drama', id: 'B' },
  { name: 'romance', id: 'C' },
  { name: 'horror', id: 'D' },
  { name: 'comedy', id: 'E' },
];
@Component({
  selector: 'app-movie-add',
  templateUrl: './movie-add.component.html',
  styleUrls: ['./movie-add.component.css']
})
export class MovieAddComponent implements OnInit{
  protected genres: Genre[] = GENRE;

  public genreCTRL: UntypedFormControl = new UntypedFormControl();

  public genreFilterCTRL: UntypedFormControl = new UntypedFormControl();

  public filteredGenres: ReplaySubject<Genre[]> = new ReplaySubject<Genre[]>(1);

  @ViewChild('singleSelect', { static: true })
  singleSelect!: MatSelect;
  protected _onDestroy = new Subject<void>();

  isIndeterminate = false;
  isChecked = false;


  ngOnInit() {
    this.genreCTRL.setValue([this.genres[1], this.genres[2]]);

    this.filteredGenres.next(this.genres.slice());

    this.genreFilterCTRL.valueChanges
      .pipe(takeUntil(this._onDestroy))
      .subscribe(() => {
        this.filterGenres();
      });
  }

  ngAfterViewInit() {
    this.setInitialValue();
  }

  ngOnDestroy() {
    this._onDestroy.next();
    this._onDestroy.complete();
  }

  protected setInitialValue() {
    this.filteredGenres
      .pipe(take(1), takeUntil(this._onDestroy))
      .subscribe(() => {
        this.singleSelect.compareWith = (a: Genre, b: Genre) =>
          a && b && a.id === b.id;
      });
  }

  protected filterGenres() {
    if (!this.genres) {
      return;
    }
    let search = this.genreFilterCTRL.value;
    if (!search) {
      this.filteredGenres.next(this.genres.slice());
      return;
    } else {
      search = search.toLowerCase();
    }
    this.filteredGenres.next(
      this.genres.filter((genre) => genre.name.toLowerCase().indexOf(search) > -1)
    );
  }

  toggleSelectAll(selectAllValue: boolean) {
    this.filteredGenres
      .pipe(take(1), takeUntil(this._onDestroy))
      .subscribe((val) => {
        if (selectAllValue) {
          this.genreCTRL.patchValue(val);
        } else {
          this.genreCTRL.patchValue([]);
        }
      });
  }
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
  goBack() {
    window.history.back();
  }
  newMovie(): void {
    this.submitted = false;
    this.movie = {
      ageRestricted: "", description: "", duration: undefined, genre: "", rating: undefined,
      name: ''
    };
  }
}
