import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs';
import { HttpParams } from '@angular/common/http';
import { Page } from '../../../shared/models';
import { AuthHttp } from '../../../core/security';

@Injectable({
    providedIn: 'root'
  })
  export class BusinessStudentService {
  
    apiUrl: string;
  
    constructor(private http: AuthHttp) {
      this.apiUrl = `${environment.apiUrl}/business-students`;
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
  }  