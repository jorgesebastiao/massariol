import { Injectable } from '@angular/core';
import { AuthHttp } from '../../../core/security';
import {environment} from '../../../../environments/environment';
import {Observable} from 'rxjs';
import {Page} from '../../../shared/models';
import {HttpParams} from '@angular/common/http';
import { SupervisorCreateCommand, SupervisorUpdateCommand, Supervisor } from './supervisor.model';

@Injectable({
  providedIn: 'root'
})
export class SupervisorService {


  apiUrl: string;

  constructor(private http: AuthHttp) {
    this.apiUrl = `${environment.apiUrl}/supervisors`;
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

  getById(id: number): Observable<Supervisor> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  post(supervisorCreateCommand: SupervisorCreateCommand): Observable<any> {
    return this.http.post(this.apiUrl, JSON.stringify(supervisorCreateCommand));
  }

  put(supervisorUpdateCommand: SupervisorUpdateCommand): Observable<any> {
    return this.http.put(this.apiUrl, JSON.stringify(supervisorUpdateCommand));
  }
}
