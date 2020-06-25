import { Injectable } from '@angular/core';
import {AuthHttp} from '../../security/auth-http';
import {environment} from '../../../../environments/environment';
import {SignatureCreateCommand} from './signature.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SignatureService {
  apiUrl: string;

  constructor(private http: AuthHttp) {
    this.apiUrl = `${environment.apiUrl}/signatures`;
  }

  post(signatureCreateCommand: SignatureCreateCommand): Observable<any> {
    return this.http.post(this.apiUrl, JSON.stringify(signatureCreateCommand));
  }
}
