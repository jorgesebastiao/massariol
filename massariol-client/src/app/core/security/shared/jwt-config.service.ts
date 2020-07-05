import {Injectable} from '@angular/core';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class JwtConfigService {
  public blacklistedRoutes: (string|RegExp)[];
  public whitelistedDomains: string[];
  constructor() {
    this.blacklistedRoutes = environment.tokenBlacklistedRoutes;
    this.whitelistedDomains = environment.tokenWhitelistedDomains;
  }
}
