import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignatureEditComponent } from './signature-edit/signature-edit.component';
import { AuthGuard } from '../../core/security';

const routes: Routes = [
  {
    path: '',
    component: SignatureEditComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SignaturesRoutingModule { }
