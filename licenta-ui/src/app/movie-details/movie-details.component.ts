import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {Movie} from "../model/movie";
import {MovieService} from "../service/movie-service";
import {ActivatedRoute, Router} from "@angular/router";
import {ReplaySubject, Subject, take, takeUntil} from "rxjs";
import {MatSelect} from "@angular/material/select";
import {UntypedFormControl} from "@angular/forms";

export const GENRE = ["action", "drama", "romance", "horror", "comedy"];

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
  protected genres: string[] = GENRE;

  public genreCTRL: UntypedFormControl = new UntypedFormControl();

  public genreFilterCTRL: UntypedFormControl = new UntypedFormControl();

  public filteredGenres: ReplaySubject<string[]> = new ReplaySubject<string[]>(1);

  @ViewChild('singleSelect', {static: true})
  singleSelect!: MatSelect;
  protected _onDestroy = new Subject<void>();

  isIndeterminate = false;
  isChecked = false;

  goBack() {
    window.history.back();
  }

  message = '';

  constructor(
    private movieService: MovieService,
    private route: ActivatedRoute,
    private router: Router) {
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

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getMovie(this.route.snapshot.params["id"]);
    }
    this.filteredGenres.next(this.genres.slice());

    this.genreFilterCTRL.valueChanges
      .pipe(takeUntil(this._onDestroy))
      .subscribe(() => {
        this.filterGenres();
      });
    this.setInitialValue();
  }

  protected setInitialValue() {
    this.filteredGenres
      .pipe(take(1), takeUntil(this._onDestroy))
      .subscribe(() => {
        this.singleSelect.compareWith = (a: string, b: string) =>
          a === b;
      });
    console.log(this.currentMovie)

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

  getMovie(id: string): void {
    this.movieService.get(id)
      .subscribe({
        next: (data) => {
          this.currentMovie = data;
          this.genreCTRL.setValue(this.currentMovie.genre.split(','));
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updateMovie(): void {
    this.message = '';
    this.currentMovie.genre = this.genreCTRL.value.join(',');
    this.movieService.update(this.currentMovie.id, this.currentMovie)
      .subscribe({
        next: (res) => {
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
