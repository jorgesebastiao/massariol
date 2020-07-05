import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MassariolFooterComponent } from './massariol-footer.component';

@NgModule({
    imports: [
      CommonModule,
    ],
    declarations: [ MassariolFooterComponent],
    exports: [ MassariolFooterComponent]
  })
  export class MassariolFooterModule { }