import { NgModule } from '@angular/core';
import { CompanyService } from './company.service';
import { SecuritySharedModule } from '../../../core/security';

@NgModule({
  declarations: [],
  imports: [
    SecuritySharedModule
  ],
  providers: [ CompanyService  ]
})
export class CompanySharedModule { }
