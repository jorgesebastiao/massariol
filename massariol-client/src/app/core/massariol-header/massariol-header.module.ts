import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MassariolHeaderComponent } from './massariol-header.component';
import { MassariolNavbarModule } from '../massariol-navbar/massariol-navbar.module';

@NgModule({
    imports: [
      CommonModule,
      MassariolNavbarModule
    ],
    declarations: [ MassariolHeaderComponent],
    exports: [ MassariolHeaderComponent]
  })
  export class MassariolHeaderModule { }