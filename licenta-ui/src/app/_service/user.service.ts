import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserAuthService} from "./user-auth.service";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  PATH_OF_API = 'http://localhost:8080';

  constructor(
    private httpclient: HttpClient,
    private userAuthService: UserAuthService
  ) {
  }


  public login(loginData: any) {
    return this.httpclient.post(this.PATH_OF_API + "/authenticate", loginData)
  }

  public roleMatch(allowedRoles: string | any[]): boolean {
    let isMatch = false;
    const userRoles: any = this.userAuthService.getRoles();

    if (userRoles != null && userRoles) {
      for (const element of userRoles) {
        for (let j = 0; j < allowedRoles.length; j++) {
          if (element.roleName === allowedRoles[j]) {
            isMatch = true;
            return isMatch;
          }
        }
      }
    }
    return isMatch;
  }
}
