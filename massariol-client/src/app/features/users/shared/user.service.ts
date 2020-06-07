import { Injectable } from '@angular/core';
import {AuthHttp} from '../../security/auth-http';
import {environment} from '../../../../environments/environment';
import {Observable} from 'rxjs';
import { CompanyUserCreateCommand, CompanyUserUpdateCommand} from './user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  apiUrl: string;

  constructor(private http: AuthHttp) {
    this.apiUrl = `${environment.apiUrl}/users`;
  }

  getByCompanyId(companyId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/companies/${companyId}`);
  }

  getByCpf(cpf: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/cpf/${cpf}`);
  }

  post(companyUserCreateCommand: CompanyUserCreateCommand): Observable<any> {
    return this.http.post(this.apiUrl, JSON.stringify(companyUserCreateCommand));
  }

  put(companyUserUpdateCommand: CompanyUserUpdateCommand): Observable<any> {
    return this.http.put(this.apiUrl, JSON.stringify(companyUserUpdateCommand));
  }

/*
  put(user: UserUpdateCommand) {
    return this.http.put(this.apiUrl, JSON.stringify(user));
  }*/
}
