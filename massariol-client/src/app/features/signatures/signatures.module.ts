import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { SignaturesRoutingModule } from './signatures-routing.module';
import { SignaturePadModule } from 'angular2-signaturepad';
import { SignatureEditComponent } from './signature-edit/signature-edit.component';

@NgModule({
    declarations: [
        SignatureEditComponent
    ],
    imports: [
        SignaturesRoutingModule,
        SharedModule,
        SignaturePadModule 
        ],
    entryComponents: []
})
export class SignaturesModule { }