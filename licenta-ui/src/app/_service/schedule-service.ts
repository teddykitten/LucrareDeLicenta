import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Schedule} from "../model/schedule";
import {ExtendedSchedule} from "../model/extended-schedule";

const baseUrl = 'http://localhost:8080/cinema/schedule';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {
  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Schedule[]> {
    return this.http.get<Schedule[]>(baseUrl);
  }

  public findAllExtended(): Observable<ExtendedSchedule[]>{
    return this.http.get<ExtendedSchedule[]>("http://localhost:8080/cinema/extendedSchedule");
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

  get(id: any): Observable<Schedule> {
    return this.http.get<Schedule>(`${baseUrl}/${id}`);
  }

}
