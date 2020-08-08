import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
  })
  export class ConfirmationService {

    private _result: Promise<any>;
    private _resolve: any;
    private _reject: any;

    constructor() {
        this._result = new Promise((resolve, reject) => {
            this._resolve = resolve;
            this._reject = reject;
        });
    }
  }