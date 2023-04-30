import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Actor} from '../model/actor';
import {Observable} from 'rxjs';

const actorUrlList = 'http://localhost:8080/cinema/actor/all';
const actorUrlAdd = 'http://localhost:8080/cinema/actor/add';

@Injectable({
  providedIn: 'root'
})
export class ActorService {

  private actorUrl: string;

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Actor[]> {
    return this.http.get<Actor[]>(actorUrlList);
  }

  create(data: any): Observable<any> {
    return this.http.post(actorUrlAdd, data);
  }
}
