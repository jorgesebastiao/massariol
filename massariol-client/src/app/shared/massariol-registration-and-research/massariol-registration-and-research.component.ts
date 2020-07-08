import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {debounceTime, distinctUntilChanged} from 'rxjs/operators';

@Component({
  selector: 'app-massariol-registration-and-research',
  templateUrl: './massariol-registration-and-research.component.html',
  styleUrls: ['./massariol-registration-and-research.component.scss']
})
export class MassariolRegistrationAndResearchComponent implements OnInit {

  @Input() buttonIcon: string;
  @Input() showButton: boolean;
  @Input() buttonTitle: string;
  @Input() brand: string;
  @Input() placeholder: string;
  @Output() searchChange: EventEmitter<any> = new EventEmitter();
  @Output() newRegisterChange: EventEmitter<any> = new EventEmitter();
  searchForm: FormGroup;
  searchControl: FormControl;

  constructor(private formBuilder: FormBuilder) {

  }

  ngOnInit() {
    if (this.buttonIcon === undefined || this.buttonIcon === null) {
      this.buttonIcon = 'far fa-plus-square fa-lg';
    }else{
      this.buttonIcon += ' fa-lg';
    }

    if (this.buttonTitle === undefined || this.buttonTitle === null) {
      this.buttonTitle = 'Novo';
    }

    if (this.showButton === undefined || this.showButton === null) {
      this.showButton = true;
    }

    this.searchControl = this.formBuilder.control('');
    this.searchForm = this.formBuilder.group({
      search: this.searchControl
    });

    this.searchControl.valueChanges.pipe(debounceTime(500),
      distinctUntilChanged())
      .subscribe(search => this.searchChange.emit({search: search}));
  }

  newRegisterChanged() {
    this.newRegisterChange.emit();
  }
}
