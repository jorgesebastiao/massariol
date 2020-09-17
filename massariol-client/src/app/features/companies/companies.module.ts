import { NgModule } from '@angular/core';

import { CompaniesRoutingModule } from './companies-routing.module';
import { CompaniesComponent } from './companies.component';
import { CompaniesListComponent } from './companies-list/companies-list.component';
import {SharedModule} from '../../shared/shared.module';
import {UserSharedModule} from '../users/shared/user-shared.module';
import { CompanyEditComponent } from './company-edit/company-edit.component';
import { CompanySharedModule } from './shared/company-shared.module';

@NgModule({
  imports: [
    CompaniesRoutingModule,
    SharedModule,
    CompanySharedModule,
    UserSharedModule
  ],
  declarations: [CompaniesComponent,
    CompaniesListComponent,
    CompanyEditComponent
  ],
  entryComponents: [
    CompanyEditComponent
  ]

})
export class CompaniesModule { }
