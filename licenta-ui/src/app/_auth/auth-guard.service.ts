import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from ".././_service/auth.service";


@Injectable({
  providedIn: 'root',
})
export class AuthGuard {

  constructor(private authService: AuthService, private router: Router) {
  }

  public canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.authService.isLoggedIn() && this.authService.isUserInRole(next.routeConfig?.data?.['role'])) {
      return true;
    } else {
      this.router.navigateByUrl("/login");
      return false;
    }
  }
}
