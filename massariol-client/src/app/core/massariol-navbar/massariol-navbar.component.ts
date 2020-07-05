import { Component, OnInit } from '@angular/core';
import {AuthService, LogoutService} from '../../features/security';
import {Router} from '@angular/router';
import {MassariolNavbarService} from './shared/massariol-navbar.service';
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-massariol-navbar',
  templateUrl: './massariol-navbar.component.html',
  styleUrls: ['./massariol-navbar.component.scss'],
  animations: [
    trigger(
      'enterAnimation', [
        transition(':enter', [
          style({ opacity: 0}),
          animate('500ms', style({opacity: 1}))
        ]),
        transition(':leave', [
          style({ opacity: 1}),
          animate('500ms', style({opacity: 0}))
        ])
      ]
    )
  ],
})

export class MassariolNavbarComponent implements OnInit {

  constructor(private logoutService: LogoutService,
              private router: Router,
              public navbarService: MassariolNavbarService,
              public  authService: AuthService) { }

  ngOnInit() {
  }

  public logout() {
    this.logoutService.logout()
      .then( () => {
        this.router.navigate(['/login']);
      });
  }

  public selectedMenu(route) {
    return this.router.url === route;
  }

}
