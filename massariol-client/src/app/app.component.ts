import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {MassariolNavbarService} from './core/massariol-navbar/shared/massariol-navbar.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private router: Router,
              public navbarService: MassariolNavbarService) { }

  displayNavBar() {
    return this.router.url !== '/login';
  }
}
