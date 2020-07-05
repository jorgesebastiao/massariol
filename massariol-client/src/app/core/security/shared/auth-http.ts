import {Injectable} from '@angular/core';
import {HttpClient, HttpHandler} from '@angular/common/http';
import {Observable, from} from 'rxjs';
import {AuthService} from './auth.service';

export class NotAuthenticatedError {
}

@Injectable({
  providedIn: 'root'
})
export class AuthHttp extends HttpClient {

  constructor(
    private authService: AuthService,
    private httpHandler: HttpHandler
  ) {
    super(httpHandler);
  }

  public delete<T>(url: string, options?: any): Observable<T> {
    return this.makeRequest<T>(() => super.delete<T>(url, options));
  }

  public patch<T>(url: string, body: any, options?: any): Observable<T> {
    return this.makeRequest<T>(() => super.patch<T>(url, options));
  }

  public head<T>(url: string, options?: any): Observable<T> {
    return this.makeRequest<T>(() => super.head<T>(url, options));
  }

  public options<T>(url: string, options?: any): Observable<T> {
    return this.makeRequest<T>(() => super.options<T>(url, options));
  }

  public get<T>(url: string, options?: any): Observable<T> {
    return this.makeRequest<T>(() => super.get<T>(url, options));
  }

  public post<T>(url: string, body: any, options?: any): Observable<T> {
    return this.makeRequest<T>(() => super.post<T>(url, body, options));
  }

  public put<T>(url: string, body: any, options?: any): Observable<T> {
    return this.makeRequest<T>(() => super.put<T>(url, body, options));
  }

  private makeRequest<T>(fn: Function): Observable<T> {
    if (this.authService.isAccessTokenInvalid()) {
      const callingNewAccessToken = this.authService.refreshToken()
        .then(() => {
          if (!this.authService.isValidToken()) {
            throw new NotAuthenticatedError();
          }
          return fn().toPromise();
        });

      return from(callingNewAccessToken);
    } else {
      return fn();
    }
  }
}
