import {
  HostListener,
  Input,
  ElementRef, Directive
} from '@angular/core';
import {
  NG_VALUE_ACCESSOR, ControlValueAccessor
} from '@angular/forms';

@Directive({
  selector: '[appMaskFormatter]',
  providers: [{
    provide: NG_VALUE_ACCESSOR,
    useExisting: MaskFormatterDirective,
    multi: true
  }]
})
export class MaskFormatterDirective implements ControlValueAccessor {

  onTouched: any;
  onChange: any;

  @Input('appMaskFormatter') appMaskFormatter: string;

  constructor(private el: ElementRef) {}

  writeValue(value: any): void {
    if (value) {
      this.el.nativeElement.value = this.applyMask(value);
    }
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  @HostListener('keyup', ['$event'])
  onKeyup($event: any) {
    const value = $event.target.value.replace(/\D/g, '');

    // retorna caso pressionado backspace
    if ($event.keyCode === 8) {
      this.onChange(value);
      return;
    }

    const pad = this.appMaskFormatter.replace(/\D/g, '').replace(/9/g, '_');
    if (value.length <= pad.length) {
      this.onChange(value);
    }

    $event.target.value = this.applyMask(value);
  }

  @HostListener('blur', ['$event'])
  onBlur($event: any) {
    if ($event.target.value.length === this.appMaskFormatter.length) {
      return;
    }
    this.onChange('');
    $event.target.value = '';
  }

  /**
   * Aplica a máscara a determinado value.
   *
   * param string value
   * return string
   */
  applyMask(value: string): string {
    value = value.replace(/\D/g, '');
    const pad = this.appMaskFormatter.replace(/\D/g, '').replace(/9/g, '_');
    const valueMask = value + pad.substring(0, pad.length - value.length);
    let valueMaskPos = 0;

    value = '';
    for (let i = 0; i < this.appMaskFormatter.length; i++) {
      if (isNaN( parseInt(this.appMaskFormatter.charAt(i)))) {
        value += this.appMaskFormatter.charAt(i);
      } else {
        value += valueMask[valueMaskPos++];
      }
    }

    if (value.indexOf('_') > -1) {
      value = value.substr(0, value.indexOf('_'));
    }
    return value;
  }
}
