import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuard} from '../security';
import {SupervisorsListComponent} from './supervisors-list/supervisors-list.component';

const routes: Routes = [
  {
    path: '',
    component: SupervisorsListComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SupervisorsRoutingModule { }
