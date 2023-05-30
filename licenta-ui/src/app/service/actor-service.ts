import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Actor} from "../model/actor";

const baseUrl = 'http://localhost:8080/cinema/actor';

@Injectable({
  providedIn: 'root'
})
export class ActorService {

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Actor[]> {
    return this.http.get<Actor[]>(baseUrl);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  get(id: any): Observable<Actor> {
    return this.http.get<Actor>(`${baseUrl}/${id}`);
  }

  findByName(name: any): Observable<Actor[]> {
    return this.http.get<Actor[]>(`${baseUrl}?name=${name}`);
  }
}
