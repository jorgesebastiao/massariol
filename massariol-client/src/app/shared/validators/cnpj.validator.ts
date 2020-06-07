import {AbstractControl} from '@angular/forms';

export class CnpjValidator {
  static validate(control: AbstractControl): { [key: string]: boolean } {
    if (this.isValidCnpj(control.value)) {
      return null;
    }

    return {'cnpjInvalid': true};
  }

  static isValidCnpj(cnpj: any): boolean {
    cnpj = !cnpj || cnpj.replace(/\D/g, '');

    const cnpjsInvsRegex = /1{14}|2{14}|3{14}|4{14}|5{14}|6{14}|7{14}|8{14}|9{14}|0{14}/;

    if (!cnpj || cnpj.length !== 14 || cnpjsInvsRegex.test(cnpj)) {
      return false;
    }

    let size = cnpj.length - 2;
    let numbers = cnpj.substring(0, size);
    const digits = cnpj.substring(size);
    let sum = 0;
    let position = size - 7;

    for (let index = size; index >= 1; index--) {
      sum += numbers.charAt(size - index) * position--;
      if (position < 2) {
        position = 9;
      }
    }

    let result = sum % 11 < 2 ? 0 : 11 - sum % 11;
    if (result !== parseInt(digits.charAt(0), 10)) {
      return false;
    }

    size += 1;
    numbers = cnpj.substring(0, size);
    sum = 0;
    position = size - 7;

    for (let index = size; index >= 1; index--) {
      sum += numbers.charAt(size - index) * position--;
      if (position < 2) {
        position = 9;
      }
    }

    result = sum % 11 < 2 ? 0 : 11 - sum % 11;

    return (result === parseInt(digits.charAt(1), 10));
  }
}
