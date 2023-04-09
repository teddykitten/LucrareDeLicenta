import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
// @ts-ignore
import {Actor} from '../model/actor';
// @ts-ignore
import {Observable} from 'rxjs/Observable';

@Injectable()
export class ActorServiceService {

  private actorUrl: string;

  constructor(private http: HttpClient) {
    this.actorUrl = 'http://localhost:8080/actor/all';
  }

  public findAll(): Observable<Actor[]> {
    return this.http.get<Actor[]>(this.actorUrl);
  }

  public save(actor: Actor) {
    return this.http.post<Actor>(this.actorUrl, actor);
  }
}
