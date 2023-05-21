import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Movie} from '../model/actor';
import {Observable} from 'rxjs';

const baseUrl = 'http://localhost:8080/cinema/actor';

@Injectable({
  providedIn: 'root'
})
export class ActorService {

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Movie[]> {
    return this.http.get<Movie[]>(baseUrl);
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

  get(id: any): Observable<Movie> {
    return this.http.get<Movie>(`${baseUrl}/${id}`);
  }

  findByName(name: any): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${baseUrl}?name=${name}`);
  }
}
