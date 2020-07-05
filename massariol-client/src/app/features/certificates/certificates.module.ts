import {NgModule} from '@angular/core';

import {CertificatesRoutingModule} from './certificates-routing.module';
import {CertificatesComponent} from './certificates.component';
import {CertificatesListComponent} from './certificates-list/certificates-list.component';
import {SharedModule} from '../../shared/shared.module';
import {StudentSharedModule} from '../students/shared/student-shared.module';
import {CertificateListForPrintComponent} from './certificate-list-for-print/certificate-list-for-print.component';
import { BusinessStudentSharedModule } from '../business-students/shared/business-students-shared.module';
import { SecuritySharedModule } from '../../core/security';
import { CertificateSharedModule } from './shared/certificate-shared.module';

@NgModule({
  declarations: [
    CertificatesComponent,
    CertificatesListComponent,
    CertificateListForPrintComponent],
  imports: [
    SharedModule,
    SecuritySharedModule,
    CertificatesRoutingModule,
    CertificateSharedModule,
    StudentSharedModule,
    BusinessStudentSharedModule
  ],
  entryComponents: [
    CertificateListForPrintComponent
  ]
})
export class CertificatesModule {
}
