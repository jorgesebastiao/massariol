import { NgModule } from '@angular/core';

import {SecurityComponent} from './security.component';
import {SharedModule} from '../../shared/shared.module';
import {LoginComponent} from './login/login.component';
import {RecoverPasswordComponent} from './recover-password/recover-password.component';
import {JWT_OPTIONS, JwtModule} from '@auth0/angular-jwt';
import {HTTP_INTERCEPTORS} from '@angular/common/http'
import { JwtOptionsFactory } from './shared/jwt-options-factory';
import { JwtConfigService, SecuritySharedModule, AuthInterceptor } from './shared';
import { SecurityRoutingModule } from './security-routing.module';

@NgModule({
  declarations: [SecurityComponent, 
    LoginComponent, 
    RecoverPasswordComponent],
  imports: [
    SharedModule,
    SecurityRoutingModule,
    SecuritySharedModule,
    JwtModule.forRoot({
      jwtOptionsProvider: {
        provide: JWT_OPTIONS,
        useFactory: JwtOptionsFactory,
        deps: [JwtConfigService],
      }
    })
  ],
  providers: [
    JwtConfigService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
  ]
})
export class SecurityModule { }
