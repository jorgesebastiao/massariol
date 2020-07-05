import { NgModule } from '@angular/core';
import { LayoutComponent } from './layout.component';
import { SharedModule } from '../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { MassariolHeaderModule } from '../massariol-header/massariol-header.module';
import { MassariolNavbarModule } from '../massariol-navbar/massariol-navbar.module';
import { MassariolFooterModule } from '../massariol-footer/massariol-footer.module';

@NgModule({
    declarations: [LayoutComponent],
    imports: [
      MassariolHeaderModule,
      MassariolNavbarModule,
      MassariolFooterModule,
      RouterModule,
      SharedModule
    ]
  })
export class LayoutModule { }