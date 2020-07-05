import { Component, OnInit } from '@angular/core';
import { MassariolNavbarService } from '../massariol-navbar/shared/massariol-navbar.service';

@Component({
  selector: 'app-massariol-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  constructor(public navbarService: MassariolNavbarService) {
    
   }

  ngOnInit() {
  }

}
