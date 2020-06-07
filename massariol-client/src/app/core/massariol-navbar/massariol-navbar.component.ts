import { Component, OnInit } from '@angular/core';
import {AuthService, LogoutService} from '../../features/security';
import {Router} from '@angular/router';

@Component({
  selector: 'app-massariol-navbar',
  templateUrl: './massariol-navbar.component.html',
  styleUrls: ['./massariol-navbar.component.scss']
})

export class MassariolNavbarComponent implements OnInit {

  constructor(private logoutService: LogoutService,
              private router: Router,
              public  authService: AuthService) { }

  ngOnInit() {
  }

  public logout() {
    this.logoutService.logout()
      .then( () => {
        this.router.navigate(['/login']);
      });
  }
}
