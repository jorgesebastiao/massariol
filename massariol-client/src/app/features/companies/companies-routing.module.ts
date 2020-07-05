import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CompaniesListComponent} from './companies-list/companies-list.component';
import { AuthGuard } from '../../core/security';

const routes: Routes = [
  {
    path: '',
    component: CompaniesListComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompaniesRoutingModule { }
