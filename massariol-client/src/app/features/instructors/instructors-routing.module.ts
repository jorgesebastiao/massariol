import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuard} from '../security';
import {InstructorsListComponent} from './instructors-list/instructors-list.component';

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
