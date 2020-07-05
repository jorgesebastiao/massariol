import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SecurityComponent} from './security.component';
import {LoginComponent} from './login/login.component';
import {RecoverPasswordComponent} from './recover-password/recover-password.component';

const routes: Routes = [
  {path: 'login', component: SecurityComponent,
    children: [
      {path: '', component: LoginComponent},
      {path: 'recover-password',  component: RecoverPasswordComponent},
    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SecurityRoutingModule { }
