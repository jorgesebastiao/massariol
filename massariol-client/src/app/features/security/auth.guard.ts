import {ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router, RouterStateSnapshot} from '@angular/router';
import {Injectable} from '@angular/core';
import {AuthService} from './auth.service';
import {Observable} from 'rxjs';

@Injectable()
export class AuthGuard implements CanLoad, CanActivate {

  constructor(private authService: AuthService,
              private router: Router) {
  }

  canLoad(route: Route): Observable<boolean> | Promise<boolean> | boolean {

    return true;
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):  Observable<boolean> | Promise<boolean> | boolean {
    if (this.authService.isAccessTokenInvalid()) {
      return this.authService.refreshToken()
        .then(() => {
          if (this.authService.isAccessTokenInvalid()) {
            this.router.navigate(['/login']).then(
              () => {
                return false;
              }
            );
          }
          return true;
        });
    } else if (route.data.roles && !this.authService.haveAnyPermission(route.data.roles)) {
      this.router.navigate(['/nao-autorizado']).then(() => {
        return false;
      });
    }
    return true;
  }
}
