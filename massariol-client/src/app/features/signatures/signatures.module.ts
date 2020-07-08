import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { SignaturesRoutingModule } from './signatures-routing.module';
import { SignaturePadModule } from 'angular2-signaturepad';
import { SignatureEditComponent } from './signature-edit/signature-edit.component';
import { SignatureSharedModule } from './shared/signature-shared.module';

@NgModule({
    declarations: [
        SignatureEditComponent
    ],
    imports: [
        SignaturesRoutingModule,
        SharedModule,
        SignatureSharedModule,
        SignaturePadModule 
        ],
    entryComponents: []
})
export class SignaturesModule { }