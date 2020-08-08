import { LOCALE_ID, NgModule, Optional, SkipSelf, ErrorHandler } from '@angular/core';
import { CommonModule, registerLocaleData } from '@angular/common';
import { RouterModule, Router } from '@angular/router';

import { FormService } from '../shared/services/form.service';
import { NotAuthorizedComponent } from './not-authorized/not-authorized.component';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { ToastService } from '../shared/services/toast.service';
import { CepService } from '../shared/services/cep.service';

import localePt from '@angular/common/locales/pt';
import { BsModalService } from 'ngx-bootstrap/modal';
import { NgxLoadingModule } from 'ngx-loading';
import { LayoutModule } from './layout/layout.module';
import { SecurityModule } from './security';
import { ErrorHandlerService } from './services/error-handler.service';


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
    LayoutModule,
    SecurityModule,
  ],
  declarations: [
    NotAuthorizedComponent],
  exports: [
    ToastrModule,
    NgxLoadingModule,
    NotAuthorizedComponent],
  providers: [
    CepService,
    FormService,
    ToastrService,
    ToastService,
    BsModalService,
    {
      provide: LOCALE_ID,
      useValue: 'pt-BR',
    },
    {
      provide: ErrorHandler,
      useClass: ErrorHandlerService
    },
  ]
})

export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error('CoreModule só pode ser carregado no AppModule');
    }
  }
}
