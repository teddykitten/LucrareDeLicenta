import {
  ActivatedRouteSnapshot,
  CanActivateChildFn,
  CanActivateFn,
  Router,
  RouterStateSnapshot
} from "@angular/router";
import {inject} from "@angular/core";
import {AuthenticationService} from "./authentication-service";
import {catchError, of} from "rxjs";
import {map} from "rxjs/operators";

export const canActivate: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const authService = inject(AuthenticationService);
  const router = inject(Router);

  return authService.checkLogin().pipe(
    map(() => true),
    catchError(() => {
      router.navigate(['route-to-fallback-page']);
      return of(false);
    })
  );
};

export const canActivateChild: CanActivateChildFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => canActivate(route, state);
