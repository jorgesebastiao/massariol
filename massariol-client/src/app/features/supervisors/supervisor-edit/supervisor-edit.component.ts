import { Component, OnInit } from '@angular/core';
import { CpfValidator } from '../../../shared/validators';
import { AbstractControl, Validators, FormGroup, FormBuilder } from '@angular/forms';
import { Subject } from 'rxjs';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { FormService } from '../../../shared/services/form.service';
import { ToastrService } from 'ngx-toastr';
import { SupervisorService } from '../shared/supervisor.service';
import { SupervisorUpdateCommand, SupervisorCreateCommand } from '../shared/supervisor.model';

@Component({
  selector: 'app-supervisor-edit',
  templateUrl: './supervisor-edit.component.html',
  styleUrls: ['./supervisor-edit.component.scss']
})
export class SupervisorEditComponent implements OnInit {
  public onClose: Subject<boolean>;
  supervisorForm: FormGroup;
  title: string;
  supervisorId: number;
  isLoading: boolean;
  isEdit: boolean;

  constructor(public bsModalRef: BsModalRef,
    private formBuilder: FormBuilder,
    private formService: FormService,
    private supervisorService: SupervisorService,
    private toastrService: ToastrService) {
  }

  ngOnInit() {
    this.onClose = new Subject();
    this.isEdit = false;
    this.title = 'Cadastro de supervisor';
    this.initForms();
    if (this.supervisorId) {
      this.isEdit = true;
      this.title = 'Edição de supervisor';
      this.getSupervisor();
    }
  }

  save() {
    if (this.supervisorForm.valid) {
      this.isLoading = true;
      if (this.isEdit) {
        const supervisorUpdateCommand: SupervisorUpdateCommand = new SupervisorUpdateCommand(this.supervisorForm.value, this.supervisorId);
        this.supervisorService.put(supervisorUpdateCommand)
            .subscribe(() => {
                this.toastrService.success('Edição realizada com Sucesso!');
                this.isLoading = false;
                this.onClose.next(true);
                this.bsModalRef.hide();
            }, error => {
              this.isLoading = false;
              const errorMessage = error.status == 400 ? error.error.message: 'Ocorreu erro ao processar a solicitação';
              this.toastrService.error(errorMessage);
            });
      } else {
        const supervisorCreateCommand: SupervisorCreateCommand = this.supervisorForm.value;
        this.supervisorService.post(supervisorCreateCommand).subscribe(() => {
          this.toastrService.success('Cadastro realizado com Sucesso!');
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
      this.formService.validForm(this.supervisorForm);
    }
  }

  private initForms() {
    this.supervisorForm = this.formBuilder.group({
      cpf: this.formBuilder.control('', [this.isValidCpf]),
      name: this.formBuilder.control('', [Validators.required]),
      cellPhone: this.formBuilder.control(''),
      email: this.formBuilder.control('', [Validators.email])
    });
  }

  isValidCpf(control: AbstractControl): { [p: string]: boolean } {
    return CpfValidator.validate(control);
  }

  getSupervisor() {
    this.isLoading = true;
    this.supervisorService.getById(this.supervisorId).subscribe(supervisorResponse => {
      this.supervisorForm.patchValue(supervisorResponse);
      this.isLoading = false;
    }, error => {
      this.isLoading = false;
      this.toastrService.error('Ocorreu erro na solicitação');
    });
  }
}
