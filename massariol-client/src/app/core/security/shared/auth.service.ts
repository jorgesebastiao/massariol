import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {environment} from '../../../../environments/environment';
import {JwtHelperService} from '@auth0/angular-jwt';
import {Token} from './token.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl: string;
  jwtPayload: any;

  constructor(private http: HttpClient, private jwtHelper: JwtHelperService) {
    this.apiUrl = `${environment.apiUrl}/oauth/token`;
    this.loadingToken();
  }

  login(user: string, password: string): Promise<any> {

    const httpHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
      .set('Authorization', 'Basic ' + btoa(environment.clientId + ':' + environment.secretId));

    const params = new URLSearchParams();
    params.append('client', environment.clientId);
    params.append('username', user);
    params.append('password', password);
    params.append('grant_type', 'password');

    return this.http.post<Token>(this.apiUrl, params.toString(), {
      headers: httpHeaders,
      withCredentials: true
    }).toPromise()
      .then(response => {
        this.addToken(response.access_token);
        return Promise.resolve(null);
      })
      .catch(error => {
        if (error.status === 400) {
          console.log(error);
          const failed = error.json();
          if (failed.error === 'invalid_grant') {
            return Promise.reject('usuario ou senha inválidos!!!');
          }
        }
        return Promise.reject(error);
      });
  }

  refreshToken(): Promise<void> {
    const httpHeaders = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
      .set('Authorization', 'Basic ' + btoa(environment.clientId + ':' + environment.secretId));

    const params = new URLSearchParams();
    params.append('client', environment.clientId);
    params.append('grant_type', 'refresh_token');

    return this.http.post<Token>(this.apiUrl, params.toString(), {
      headers: httpHeaders,
      withCredentials: true
    }).toPromise()
      .then(response => {
        this.addToken(response.access_token);
        return Promise.resolve(null);
      })
      .catch(error => {
        return Promise.resolve(null);
      });
  }

  private addToken(token: string) {
    this.jwtPayload = this.jwtHelper.decodeToken(token);
    localStorage.setItem(environment.tokenName, token);
  }

  getToken() {
    return localStorage.getItem(environment.tokenName);
  }

  isNotLogged(){
    const token = this.getToken();
    return token == null;
  }

  isValidToken() {
    const token = this.getToken();
    return token != null && !this.jwtHelper.isTokenExpired(token);
  }

  isAccessTokenInvalid() {
    const token = this.getToken();
    return !token || this.jwtHelper.isTokenExpired(token);
  }

  isAllowed(permission: string) {
    return this.jwtPayload && this.jwtPayload.authorities.includes(permission);
  }

  haveAnyPermission(roles: string[]) {
    for (const role of roles) {
      if (this.isAllowed(role)) {
        return true;
      }
    }
    return false;
  }

  cleanAcessToken() {
    this.jwtPayload = null;
    localStorage.removeItem(environment.tokenName);
  }

  loadingToken() {
    const token = this.getToken();
    if (token) {
      this.addToken(token);
    }
  }
}
