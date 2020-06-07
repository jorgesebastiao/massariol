import {Injectable} from '@angular/core';
import {AuthHttp} from '../../security/auth-http';
import {environment} from '../../../../environments/environment';
import {Page} from '../../../shared/models';
import {Observable} from 'rxjs';
import {HttpParams} from '@angular/common/http';
import {Instructor, InstructorCreateCommand, InstructorUpdateCommand} from './instructor.model';

@Injectable({
  providedIn: 'root'
})
export class InstructorService {
  apiUrl: string;

  constructor(private http: AuthHttp) {
    this.apiUrl = `${environment.apiUrl}/instructors`;
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

  getById(id: number): Observable<Instructor> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  post(instructorCreateCommand: InstructorCreateCommand): Observable<any> {
    return this.http.post(this.apiUrl, JSON.stringify(instructorCreateCommand));
  }

  put(instructorUpdateCommand: InstructorUpdateCommand): Observable<any> {
    return this.http.put(this.apiUrl, JSON.stringify(instructorUpdateCommand));
  }
}
