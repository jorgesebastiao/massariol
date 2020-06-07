import { Injectable } from '@angular/core';
import {AuthHttp} from '../../security/auth-http';
import {environment} from '../../../../environments/environment';
import {Page} from '../../../shared/models';
import {Observable} from 'rxjs';
import {HttpParams} from '@angular/common/http';
import { StudentCreateCommand, StudentUpdateCommand } from './student.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  apiUrl: string;

  constructor(private http: AuthHttp) {
    this.apiUrl = `${environment.apiUrl}/students`;
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

  post(studentCreateCommand: StudentCreateCommand): Observable<any> {
    return this.http.post(this.apiUrl, JSON.stringify(studentCreateCommand));
  }

  put(studentUpdateCommand: StudentUpdateCommand): Observable<any> {
    return this.http.put(this.apiUrl, JSON.stringify(studentUpdateCommand));
  }
}
