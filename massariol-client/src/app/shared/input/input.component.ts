import {Component, OnInit, Input, ContentChild, AfterContentInit} from '@angular/core';
import {FormControlName} from '@angular/forms';

@Component({
  selector: 'app-input-container',
  templateUrl: './input.component.html',
})
export class InputComponent implements OnInit, AfterContentInit {

  @Input() label: string;
  @Input() for: string;
  @Input() errorMessageParttern: string;
  @Input() errorMessageMinLenght: string;
  @Input() errorMessageMaxLenght: string;

  input: any;

  @ContentChild(FormControlName) control: FormControlName;

  constructor() {
  }

  ngOnInit() {
  }

  ngAfterContentInit() {
    this.input = this.control;
    if (this.input === undefined) {
      throw new Error('Esse componente precisa ser usado com uma diretiva formControlName');
    }
  }

  hasSuccess(): boolean {
    return this.input.valid && (this.input.dirty || this.input.touched);
  }

  hasError(): boolean {
    return this.input.invalid && (this.input.dirty || this.input.touched);
  }

  hasRequerid(): boolean {
    return this.input.invalid && (this.input.dirty || this.input.touched) && this.input.hasError('required');
  }

  hasParttern() {
    return this.input.invalid && (this.input.dirty || this.input.touched) && this.input.hasError('pattern');
  }

  hasMinLenght() {
    return this.input.invalid && (this.input.dirty || this.input.touched) && this.input.hasError('minlength');
  }

  hasMaxLength() {
    return this.input.invalid && (this.input.dirty || this.input.touched) && this.input.hasError('maxlength');
  }

  hasInvalidEmail(): boolean {
    return this.input.invalid && (this.input.dirty || this.input.touched) && this.input.hasError('email');
  }

  hasInvalidCpf(): boolean {
    return this.input.invalid && (this.input.dirty || this.input.touched) && this.input.hasError('cpfInvalid');
  }

  hasInvalidCnpj(): boolean {
    return this.input.invalid && (this.input.dirty || this.input.touched) && this.input.hasError('cnpjInvalid');
  }

  hasCpfInUse(): boolean {
    return this.input.invalid && (this.input.dirty || this.input.touched) && this.input.hasError('cpfInUse');
  }

  hasCnpjInUse(): boolean {
    return this.input.invalid && (this.input.dirty || this.input.touched) && this.input.hasError('cnpjInUse');
  }

  hasUserInUse(): boolean {
    return this.input.invalid && (this.input.dirty || this.input.touched) && this.input.hasError('userInUse');
  }
}
