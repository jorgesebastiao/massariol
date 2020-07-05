import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MassariolNavbarService {
  visible: boolean;

  constructor() { this.visible = true; }

  toggle() { this.visible = !this.visible; }
}
