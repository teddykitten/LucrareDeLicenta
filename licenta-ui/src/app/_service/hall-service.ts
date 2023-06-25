import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Hall} from "../model/hall";

const baseUrl = 'http://localhost:8080/cinema/hall';

@Injectable({
  providedIn: 'root'
})
export class HallService {

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Hall[]> {
    return this.http.get<Hall[]>(baseUrl);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  get(id: any): Observable<Hall> {
    return this.http.get<Hall>(`${baseUrl}/${id}`);
  }

  findByName(name: any): Observable<Hall[]> {
    return this.http.get<Hall[]>(`${baseUrl}?name=${name}`);
  }
}
