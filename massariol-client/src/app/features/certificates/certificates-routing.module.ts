import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CertificatesListComponent} from './certificates-list/certificates-list.component';
import { AuthGuard } from '../../core/security';

const routes: Routes = [
  {
    path: '',
    component: CertificatesListComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CertificatesRoutingModule {
}
