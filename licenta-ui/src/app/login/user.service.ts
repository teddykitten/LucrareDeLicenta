import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from "../model/user";
import {config} from "rxjs";


@Injectable()
export class UserService {
  constructor(private http: HttpClient) {
  }
}
