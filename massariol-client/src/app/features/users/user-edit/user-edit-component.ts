import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FormService } from '../../../shared/services/form.service';
import { Subject, Observable, of, concat } from 'rxjs';
import { UserService } from '../shared/user.service';
import { ToastrService } from 'ngx-toastr';
import { UserCreateCommand, UserUpdateCommand } from '../shared/user.model';
import { Page } from '../../../shared/models';
import { distinctUntilChanged, tap, switchMap, map, catchError } from 'rxjs/operators';
import { CompanyService } from '../../companies/shared/company.service';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.scss']
})
export class UserEditComponent implements OnInit {
  public onClose: Subject<boolean>;
  userForm: FormGroup;
  title: string;
  userId: number;
  isLoading: boolean;
  isEdit: boolean;

  companies: Observable<any[]>;
  companyLoading = false;
  companyInput = new Subject<string>();

  constructor(public bsModalRef: BsModalRef,
    private formBuilder: FormBuilder,
    private formService: FormService,
    private toastrService: ToastrService,
    private userService: UserService,
    private companyService: CompanyService) {

  }

  ngOnInit() {
    this.onClose = new Subject();
    this.isEdit = this.userId != null;
    this.title = 'Cadastro de usuário';
    this.initForms();
    this.initSelect();

    if (this.userId) {
      this.isEdit = true;
      this.title = 'Edição de usuário';
      this.getUser();
    } else {
      this.getCompanies();
    }
  }

  initForms() {
    this.userForm = this.formBuilder.group({
      name: this.formBuilder.control('', [Validators.required]),
      email: this.formBuilder.control('', [Validators.email, Validators.required]),
      type: this.formBuilder.control({ value: '', disabled: this.isEdit }, [Validators.required]),
      resendPassword: this.formBuilder.control({ value: false, disabled: !this.isEdit }),
      profile: this.formBuilder.control(''),
      companyId: this.formBuilder.control({ value: '', disabled: this.isEdit })
    });
  }

  initSelect() {
    this.userForm.controls['type'].setValue('MASSARIOL');
  }

  getUser() {
    this.isLoading = true;
    this.userService.getById(this.userId).subscribe(userResponse => {
      if(userResponse.company)
      this.setCompany(userResponse.company);
      this.userForm.patchValue(userResponse);
      this.isLoading = false;
    }, error => {
      this.isLoading = false;
      this.toastrService.error('Ocorreu erro na solicitação');
    });
  }

  save() {
    if (this.userForm.valid) {
      this.isLoading = true;
      if (this.isEdit) {
        const userUpdateCommand: UserUpdateCommand = new UserUpdateCommand(this.userForm.value, this.userId);
        this.userService.put(userUpdateCommand)
          .subscribe(() => {
            this.toastrService.success('Edição realizada com Sucesso!');
            this.isLoading = false;
            this.onClose.next(true);
            this.bsModalRef.hide();
          }, error => {
            this.isLoading = false;
            const errorMessage = error.status == 400 ? error.error.message : 'Ocorreu erro ao processar a solicitação';
            this.toastrService.error(errorMessage);
          });
      } else {
        const userCreateCommand: UserCreateCommand = new UserCreateCommand(this.userForm.value);
        this.userService.post(userCreateCommand).subscribe(() => {
          this.toastrService.success('Cadastro realizado com Sucesso!');
          this.isLoading = false;
          this.onClose.next(true);
          this.bsModalRef.hide();
        }, error => {
          this.isLoading = false;
          const errorMessage = error.status == 400 ? error.error.message : 'Ocorreu erro ao processar a solicitação';
          this.toastrService.error(errorMessage);
        });
      }
    } else {
      this.formService.validForm(this.userForm);
    }
  }

  getCompanies() {
    const page: Page = new Page();
    this.companies = concat(
      of([]),
      this.companyInput.pipe(
        distinctUntilChanged(),
        tap(() => this.companyLoading = true),
        switchMap(term => this.companyService.getAll(page, term).pipe(
          map((data) => data.content),
          catchError(() => of([])),
          tap(() => this.companyLoading = false)
        ))
      )
    );
  }

  setCompany(company: any) {
    var newCompanies: any[] = [];
    newCompanies.push(company);
    const page: Page = new Page();
    this.companies = concat(
        of(newCompanies),
        this.companyInput.pipe(
            distinctUntilChanged(),
            tap(() => this.companyLoading = true),
            switchMap(term => this.companyService.getAll(page, term).pipe(
                map((data) => data.content),
                catchError(() => of([])),
                tap(() => this.companyLoading = false)
            ))
        )
    );
}

  onChangeType() {
    if (this.userForm.get('type').value == 'MASSARIOL') 
      this.userForm.patchValue({ profile: '' });    
  }
}