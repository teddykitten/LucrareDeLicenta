import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable()
export class AuthenticationService {
  private request: any;

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string) {
    console.log(username, password)
    var body = "username:" + username + "\n" + "password:" + password;
    console.log(body)
    let headers = new HttpHeaders();
    headers = headers.append('content-type','application/x-www-form-urlencoded');
    headers = headers.append('Access-Control-Allow-Origin','http://localhost:4200');
    var result = this.http.post<any>(`http://localhost:8080/login`, body, {'headers': headers}).subscribe()
    console.log(result)
    return result
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }
}
