import { Component, OnInit } from '@angular/core';
import {MassariolNavbarService} from '../massariol-navbar/shared/massariol-navbar.service';

@Component({
  selector: 'app-massariol-header',
  templateUrl: './massariol-header.component.html',
  styleUrls: ['./massariol-header.component.scss']
})
export class MassariolHeaderComponent implements OnInit {

  constructor(public navbarService: MassariolNavbarService) { }

  ngOnInit() {
  }

}
