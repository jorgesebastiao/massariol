
import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { FormService } from '../../../shared/services/form.service';
import { CompanyUserCreateCommand, CompanyUserUpdateCommand } from '../../users/shared/user.model';
import { UserService } from '../../users/shared/user.service';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-company-create-user',
  templateUrl: './company-create-user.component.html',
  styleUrls: ['./company-create-user.component.scss']
})
export class CompanyCreateUserComponent implements OnInit {

  public onClose: Subject<boolean>;
  userForm: FormGroup;
  title: string;
  userId: number;
  companyId: number;
  isLoading: boolean;
  hasUser: boolean;

  constructor(public bsModalRef: BsModalRef,
    private formBuilder: FormBuilder,
    private formService: FormService,
    private userService: UserService,
    private toastrService: ToastrService) {
  }

  ngOnInit() {
    this.onClose = new Subject();
    this.title = 'Cadastro de usuário';
    this.initForms();
    if (this.hasUser) {
      this.userForm.get('password').clearValidators();
      this.title = 'Edição de usuário';
      this.getUserCompany();
    }
  }

  save() {
    if (this.userForm.valid) {
      this.isLoading = true;
      if (this.hasUser) {
        const companyUserUpdateCommand: CompanyUserUpdateCommand = new CompanyUserUpdateCommand(this.userForm.value, this.userId, this.companyId); 
        this.userService.put(companyUserUpdateCommand).subscribe(() => {
          this.toastrService.success('Edição Realizada com Sucesso!');
          this.isLoading = false;
          this.onClose.next(true);
          this.bsModalRef.hide();
        }, error => {
          this.isLoading = false;
          const errorMessage = error.status == 400 ? error.error.message: 'Ocorreu erro ao processar a solicitação';
          this.toastrService.error(errorMessage);
        });

      } else {
        const companyUserCreateCommand: CompanyUserCreateCommand = new CompanyUserCreateCommand(this.userForm.value, this.companyId);
        this.userService.post(companyUserCreateCommand).subscribe(() => {
          this.toastrService.success('Cadastro Realizado com Sucesso!');
          this.isLoading = false;
          this.onClose.next(true);
          this.bsModalRef.hide();
        }, error => {
          this.isLoading = false;
          const errorMessage = error.status == 400 ? error.error.message: 'Ocorreu erro ao processar a solicitação';
          this.toastrService.error(errorMessage);
        });
      }
    } else {
      this.formService.validForm(this.userForm);
    }
  }

  getUserCompany() {
    this.isLoading = true;
   this.userService.getByCompanyId(this.companyId).subscribe(userResponse => {
     this.userId = userResponse.id;
     this.userForm.patchValue(userResponse);
     this.isLoading = false;
    }, error => {
      this.isLoading = false;
      const errorMessage = error.status == 400 ? error.error.message: 'Ocorreu erro ao processar a solicitação';
      this.toastrService.error(errorMessage);
    });
  }

  private initForms() {
    this.userForm = this.formBuilder.group({
      name: this.formBuilder.control('', [Validators.required]),
      email: this.formBuilder.control('', [Validators.email, Validators.required]),
      password: this.formBuilder.control('', [Validators.required])
    });
  }
}
