import { Injectable } from '@angular/core';
import { AuthHttp } from '../../../core/security';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs';
import { UserCreateCommand, UserUpdateCommand } from './user.model';
import { Page } from '../../../shared/models';
import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUrl: string;

  constructor(private http: AuthHttp) {
    this.apiUrl = `${environment.apiUrl}/users`;
  }


  getAll(page?: Page, filter?: string): Observable<any> {
    let httpParans: HttpParams;
    if (page) {
      httpParans = new HttpParams().set('size', page.size.toString()).set('page', page.pageNumber.toString());
    }
    if (filter) {
      httpParans = httpParans.set('filter', filter);
    }

    return this.http.get(`${this.apiUrl}`, {
      params: httpParans,
      responseType: 'json'
    });
  }

  getById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  getByCompanyId(companyId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/companies/${companyId}`);
  }

  getByCpf(cpf: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/cpf/${cpf}`);
  }

  post(user: UserCreateCommand): Observable<any> {
    return this.http.post(this.apiUrl, JSON.stringify(user));
  }

  put(user: UserUpdateCommand): Observable<any> {
    return this.http.put(this.apiUrl, JSON.stringify(user));
  }

}
