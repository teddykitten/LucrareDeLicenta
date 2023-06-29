import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Reservation} from "../model/reservation";

const baseUrl = 'http://localhost:8080/cinema/reservation';
@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) {
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  get(id: any): Observable<Reservation> {
    return this.http.get<Reservation>(`${baseUrl}/${id}`);
  }

}
