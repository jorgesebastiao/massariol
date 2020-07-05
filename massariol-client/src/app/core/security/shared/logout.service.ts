import {Injectable} from '@angular/core';

import {environment} from '../../../../environments/environment';
import {AuthService} from './auth.service';
import {AuthHttp} from './auth-http';

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  apiUrl: string;

  constructor(private authService: AuthService,
              private httpClient: AuthHttp) {
    this.apiUrl = `${environment.apiUrl}/tokens/revoke`;
  }

  logout() {
    return this.httpClient.delete(this.apiUrl, {withCredentials: true})
      .toPromise()
      .then(() => {
        this.authService.cleanAcessToken();
      });
  }
}
