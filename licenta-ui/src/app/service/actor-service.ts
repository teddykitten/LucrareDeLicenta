import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Actor} from '../model/actor';
import {Observable} from 'rxjs';

@Injectable()
export class ActorService {

  private actorUrl: string;

  constructor(private http: HttpClient) {
    this.actorUrl = 'http://localhost:8080/actor/all';
  }

  public findAll(): Observable<Actor[]> {
    return this.http.get<Actor[]>(this.actorUrl);
  }

  //
  // public save(actor: Actor) {
  //   return this.http.post<Actor>(this.actorUrl, actor);
  // }
}
