import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserAuthService} from "./user-auth.service";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  PATH_OF_API = 'http://localhost:8080';

  constructor(private httpclient: HttpClient) {
  }



  public login(loginData: any) {
    return this.httpclient.post(this.PATH_OF_API + "/authenticate", loginData)
  }
}
