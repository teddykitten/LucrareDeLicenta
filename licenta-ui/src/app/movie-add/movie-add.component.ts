import {Component, OnInit, ViewChild} from '@angular/core';
import {MovieService} from "../_service/movie-service";
import {UntypedFormControl} from "@angular/forms";
import {ReplaySubject, Subject, take, takeUntil} from "rxjs";
import {MatSelect} from "@angular/material/select";
import {Movie} from "../model/movie";

export const GENRE = ["action", "drama", "romance", "horror", "comedy"];

@Component({
  selector: 'app-movie-add',
  templateUrl: './movie-add.component.html',
  styleUrls: ['./movie-add.component.css']
})
export class MovieAddComponent implements OnInit {
  protected genres: string[] = GENRE;

  public genreCTRL: UntypedFormControl = new UntypedFormControl();

  public genreFilterCTRL: UntypedFormControl = new UntypedFormControl();

  public filteredGenres: ReplaySubject<string[]> = new ReplaySubject<string[]>(1);

  @ViewChild('singleSelect', {static: true})
  singleSelect!: MatSelect;
  protected _onDestroy = new Subject<void>();

  isIndeterminate = false;
  isChecked = false;


  ngOnInit() {
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
        this.singleSelect.compareWith = (a: string, b: string) =>
          a === b;
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
      this.genres.filter((genre) => genre.toLowerCase().indexOf(search) > -1)
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
      genre: this.genreCTRL.value.join(','),
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
      ageRestricted: "",
      description: "",
      duration: undefined,
      genre: "",
      rating: undefined,
      name: ''
    };
    this.genreCTRL.setValue("")
  }
}
