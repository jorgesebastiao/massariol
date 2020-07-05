import {Injectable} from '@angular/core';
import { AuthHttp } from '../../../core/security';
import {environment} from '../../../../environments/environment';
import {Page} from '../../../shared/models';
import {Observable} from 'rxjs';
import {HttpParams} from '@angular/common/http';
import {Company, CompanyCreateCommand, CompanyUpdateComand} from './company.model';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  apiUrl: string;

  constructor(private http: AuthHttp) {
    this.apiUrl = `${environment.apiUrl}/companies`;
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

  getById(id: number): Observable<Company> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  post(company: CompanyCreateCommand): Observable<any> {
    return this.http.post(this.apiUrl, JSON.stringify(company));
  }

  put(company: CompanyUpdateComand): Observable<any> {
    return this.http.put(this.apiUrl, JSON.stringify(company));
  }
}
