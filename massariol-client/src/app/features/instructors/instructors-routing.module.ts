import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {InstructorsListComponent} from './instructors-list/instructors-list.component';
import { AuthGuard } from '../../core/security';

const routes: Routes = [
  {
    path: '',
    component: InstructorsListComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InstructorsRoutingModule { }
