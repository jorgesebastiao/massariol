import { OnInit, Component, ViewChild } from '@angular/core';

import { SignaturePad } from 'angular2-signaturepad/signature-pad';

@Component({
    selector: 'app-signature-edit',
    templateUrl: './signature-edit.component.html',
    styleUrls: ['./signature-edit.component.scss']
})
export class SignatureEditComponent implements OnInit {

    @ViewChild(SignaturePad) signaturePad: SignaturePad;

    public signaturePadOptions: Object = {
        'minWidth': 5,
        'canvasWidth': 500,
        'canvasHeight': 300
    };

    constructor() { }

    ngOnInit() {

    }


    ngAfterViewInit() {
        this.signaturePad.set('minWidth', 5);
        this.signaturePad.clear();
    }

    drawComplete() {
        console.log(this.signaturePad.toDataURL());
    }

    drawStart() {
        console.log('begin drawing');
    }
}