import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {


  public setRoles(roles: []) {
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  public getRoles(): [] {
    const roles = localStorage.getItem('roles');
    if (roles == null) {
      return [];
    }
    return JSON.parse(roles);
  }

  public setToken(jwtToken: string) {
    localStorage.setItem('jwtToken', jwtToken);
  }

  public getToken(): string {
    const token = localStorage.getItem('jwtToken');
    if (token == null) {
      return "";
    }
    return token;
  }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn() {
    return this.getRoles() && this.getToken().length > 0;
  }
}
