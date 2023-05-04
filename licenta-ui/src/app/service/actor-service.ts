import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Actor} from '../model/actor';
import {Observable} from 'rxjs';

const actorUrlList = 'http://localhost:8080/cinema/actor/all';
const actorUrlAdd = 'http://localhost:8080/cinema/actor/add';
const actorUrlRemove = 'http://localhost:8080/cinema/actor/remove';
const actorUrlUpdate = 'http://localhost:8080/cinema/actor/update';
const actorUrlGet = 'http://localhost:8080/cinema/actor/getby';

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

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${actorUrlUpdate}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${actorUrlRemove}/${id}`);
  }

  get(id: any): Observable<Actor> {
    return this.http.get<Actor>(`${actorUrlGet}/${id}`);
  }
}
