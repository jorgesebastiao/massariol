import { environment } from '../../../../environments/environment';
import { AuthHttp } from '../../security/auth-http';
import { Injectable } from '@angular/core';
import { Page } from '../../../shared/models';
import { Observable } from 'rxjs';
import { HttpParams } from '@angular/common/http';
import { TrainingCreateCommand, TrainingUpdateCommand } from './training.model';

@Injectable({
  providedIn: 'root'
})
export class TrainingService {

  apiUrl: string;

  constructor(private http: AuthHttp) {
    this.apiUrl = `${environment.apiUrl}/trainings`;
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

  getAllByBusinessStudentId(businessStudentId: number,  page?: Page, filter?: string): Observable<any> {
    let httpParans: HttpParams;
    if (page) {
      httpParans = new HttpParams().set('size', page.size.toString()).set('page', page.pageNumber.toString());
    }
    if (filter) {
      httpParans = httpParans.set('filter', filter);
    }

    return this.http.get(`${this.apiUrl}/business-student/${businessStudentId}`, {
      params: httpParans,
      responseType: 'json'
    });
  }

  getById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  post(trainingCreateCommand: TrainingCreateCommand): Observable<any> {
    return this.http.post(this.apiUrl, JSON.stringify(trainingCreateCommand));
  }

  put(trainingUpdateCommand: TrainingUpdateCommand): Observable<any> {
    return this.http.put(this.apiUrl, JSON.stringify(trainingUpdateCommand));
  }
}