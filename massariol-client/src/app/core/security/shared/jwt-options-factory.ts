import {environment} from '../../../../environments/environment';
import {JwtConfigService} from './jwt-config.service';

export function JwtOptionsFactory(jwtConfigService: JwtConfigService) {
  return {
    tokenGetter: tokenGetter,
    whitelistedDomains: jwtConfigService.whitelistedDomains,
    blacklistedRoutes: jwtConfigService.blacklistedRoutes,
    skipWhenExpired: false
  };
}

export function tokenGetter() {
  return localStorage.getItem(environment.tokenName);
}

