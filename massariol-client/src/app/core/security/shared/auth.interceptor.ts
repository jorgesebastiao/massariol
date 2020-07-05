import {
  HttpEvent,
  HttpHandler, HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable, Injector} from '@angular/core';
import {AuthService} from './auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private  injector: Injector) {
  }

  private applyCredentials = (req: HttpRequest<any>) => {
    return req.clone({setHeaders: {'Content-Type': 'application/json'}});
  }

  intercept(request: HttpRequest<any>, next: HttpHandler)
    : Observable<HttpEvent<any>> {
    const authService = this.injector.get(AuthService);
    if (authService.isValidToken()) {
      return next.handle(this.applyCredentials(request));
    } else {
      return next.handle(request);
    }
  }
}
