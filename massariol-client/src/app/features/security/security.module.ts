import { NgModule } from '@angular/core';

import { SecurityRoutingModule } from './security-routing.module';
import {SecurityComponent} from './security.component';
import {SharedModule} from '../../shared/shared.module';
import {LoginComponent} from './login/login.component';
import {RecoverPasswordComponent} from './recover-password/recover-password.component';
import {LogoutService} from './logout.service';
import {JWT_OPTIONS, JwtModule} from '@auth0/angular-jwt';
import {JwtConfigService} from './jwt-config.service';
import {JwtOptionsFactory} from './jwt-options-factory';
import {HTTP_INTERCEPTORS} from '@angular/common/http'
import {AuthInterceptor} from './auth.interceptor';

@NgModule({
  imports: [
    SharedModule,
    SecurityRoutingModule,
    JwtModule.forRoot({
      jwtOptionsProvider: {
        provide: JWT_OPTIONS,
        useFactory: JwtOptionsFactory,
        deps: [JwtConfigService],
      }
    })
  ],
  declarations: [SecurityComponent, LoginComponent, RecoverPasswordComponent],
  providers: [
    LogoutService,
    JwtConfigService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
  ]
})
export class SecurityModule { }
