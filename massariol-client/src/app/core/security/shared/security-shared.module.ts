import { NgModule } from '@angular/core';
import { AuthHttp } from './auth-http';
import { AuthService } from './auth.service';
import { LogoutService } from './logout.service';
import { AuthGuard } from './auth.guard';
import { JwtConfigService } from './jwt-config.service';

@NgModule({
  imports: [],
  exports: [],
  declarations: [],
  providers: [
    AuthGuard,
    AuthHttp,
    AuthService,
    LogoutService,
    JwtConfigService
  ],
})
export class SecuritySharedModule { }
