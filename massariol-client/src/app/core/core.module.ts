import {LOCALE_ID, NgModule, Optional, SkipSelf} from '@angular/core';
import {CommonModule, registerLocaleData} from '@angular/common';
import {MassariolFooterComponent} from './massariol-footer/massariol-footer.component';
import {MassariolNavbarComponent} from './massariol-navbar/massariol-navbar.component';
import {RouterModule} from '@angular/router';
import {AuthGuard, AuthService} from '../features/security';
import {JwtHelperService, JwtModule} from '@auth0/angular-jwt';

import {FormService} from '../shared/services/form.service';
import {NotAuthorizedComponent} from './not-authorized/not-authorized.component';
import {AuthHttp} from '../features/security/auth-http';
import { HttpClientModule} from '@angular/common/http';
import {ToastrModule, ToastrService} from 'ngx-toastr';
import {ToastService} from '../shared/services/toast.service';
import {CepService} from '../shared/services/cep.service';

import localePt from '@angular/common/locales/pt';
import {BsModalService, ModalModule} from 'ngx-bootstrap/modal';
import {NgxLoadingModule} from 'ngx-loading';
import { MassariolHeaderComponent } from './massariol-header/massariol-header.component';


registerLocaleData(localePt);
@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule,
    NgxLoadingModule.forRoot({}),
    ToastrModule.forRoot({
      closeButton: true
    }),
  ],
  declarations: [MassariolFooterComponent,
    MassariolNavbarComponent,
    NotAuthorizedComponent,
    MassariolHeaderComponent],
  exports: [MassariolFooterComponent,
    MassariolNavbarComponent,
    MassariolHeaderComponent,
    ToastrModule,
    NgxLoadingModule,
    NotAuthorizedComponent,
    JwtModule],
  providers: [
    AuthService,
    AuthGuard,
    AuthHttp,
    CepService,
    FormService,
    JwtHelperService,
    ToastrService,
    ToastService,
    BsModalService,
    //{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
    {provide: LOCALE_ID, useValue: 'pt-BR'}]
})

export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error('CoreModule só pode ser carregado no AppModule');
    }
  }
}
