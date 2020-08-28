import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {InputComponent} from './input/input.component';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {RadioComponent} from './radio/radio.component';
import {MaskFormatterDirective} from './directives';
import {CpfPipe, CnpjPipe, CellPhone} from './pipes';
import {MassariolRegistrationAndResearchComponent} from './massariol-registration-and-research';
import {BsDatepickerModule, DatepickerModule} from 'ngx-bootstrap/datepicker';
import { ModalModule} from 'ngx-bootstrap/modal';
import {ButtonsModule} from 'ngx-bootstrap/buttons';
import {NgxLoadingModule} from 'ngx-loading';
import { NgSelectModule } from '@ng-select/ng-select';
import { ConfirmationService, ConfirmationComponent } from './confirmation';

@NgModule({
  imports: [
    CommonModule,
    NgxDatatableModule,
    ButtonsModule.forRoot(),
    BsDatepickerModule.forRoot(),
    ModalModule.forRoot(),
    DatepickerModule.forRoot(),
    NgSelectModule,
    ReactiveFormsModule,
    NgxLoadingModule
  ],
  declarations: [InputComponent,
    RadioComponent,
    MaskFormatterDirective,
    CpfPipe,
    CnpjPipe,
    CellPhone,
    MassariolRegistrationAndResearchComponent,
    ConfirmationComponent],
  exports: [InputComponent,
    NgxDatatableModule,
    NgxLoadingModule,
    ButtonsModule,
    BsDatepickerModule,
    DatepickerModule,
    NgxDatatableModule,
    ModalModule,
    ReactiveFormsModule,
    MaskFormatterDirective,
    CpfPipe,
    CnpjPipe,
    CellPhone,
    NgSelectModule,
    MassariolRegistrationAndResearchComponent,
    CommonModule],
    providers:[
      ConfirmationService
    ]
})
export class SharedModule {
}
