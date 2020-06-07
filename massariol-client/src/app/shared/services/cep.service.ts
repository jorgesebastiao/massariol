import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CepService {

  constructor(private http: HttpClient) { }

  searchZipCode( cep: string): Observable<any> {
    return this.http.get(`${environment.apiViaCep}/${cep}/json`);
  }
}
