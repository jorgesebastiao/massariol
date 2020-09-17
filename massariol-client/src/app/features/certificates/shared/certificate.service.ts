import {Injectable} from '@angular/core';
import { AuthHttp } from '../../../core/security';
import {environment} from '../../../../environments/environment';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CertificateService {

  apiUrl: string;

  constructor(private http: AuthHttp) {
    this.apiUrl = `${environment.apiUrl}/certificates`;
  }

  getReportTraining(trainingId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/training/${trainingId}`, {responseType: 'blob'});
  }

  getReport(trainingId: number, businessStudentId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/training/${trainingId}/business-student/${businessStudentId}`, {responseType: 'blob'});
  }
}
