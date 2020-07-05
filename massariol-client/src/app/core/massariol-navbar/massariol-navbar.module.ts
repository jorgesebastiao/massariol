import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MassariolNavbarComponent } from './massariol-navbar.component';
import { RouterModule } from '@angular/router';
import { SecurityModule } from '../security';

@NgModule({
    imports: [
      CommonModule,
      RouterModule,
      SecurityModule
    ],
    declarations: [ MassariolNavbarComponent],
    exports: [ MassariolNavbarComponent]
  })
  export class MassariolNavbarModule { }