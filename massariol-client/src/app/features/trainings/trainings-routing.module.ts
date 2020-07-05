import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TrainingsListComponent } from './trainings-list/trainings-list.component';
import { AuthGuard } from '../../core/security';

const routes: Routes = [
  {
    path: '',
    component: TrainingsListComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TrainingsRoutingModule { }
