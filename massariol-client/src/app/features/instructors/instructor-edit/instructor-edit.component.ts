import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { FormService } from '../../../shared/services/form.service';
import { InstructorService } from '../shared/instructor.service';
import { ToastrService } from 'ngx-toastr';
import { InstructorUpdateCommand, InstructorCreateCommand } from '../shared/instructor.model';
import { CpfValidator } from '../../../shared/validators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-instructor-edit',
  templateUrl: './instructor-edit.component.html',
  styleUrls: ['./instructor-edit.component.scss']
})
export class InstructorEditComponent implements OnInit {

  public onClose: Subject<boolean>;
  instructorForm: FormGroup;
  title: string;
  instructorId: number;
  isLoading: boolean;
  isEdit: boolean;

  constructor(public bsModalRef: BsModalRef,
    private formBuilder: FormBuilder,
    private formService: FormService,
    private instructorService: InstructorService,
    private toastrService: ToastrService) {
  }

  ngOnInit() {
    this.onClose = new Subject();
    this.isEdit = false;
    this.title = 'Cadastro de instrutor';
    this.initForms();
    if (this.instructorId) {
      this.isEdit = true;
      this.title = 'Edição de instrutor';
      this.getInstructor();
    }
  }

  save() {
    if (this.instructorForm.valid) {
      this.isLoading = true;
      if (this.isEdit) {
        const instructorUpdateCommand: InstructorUpdateCommand = new InstructorUpdateCommand(this.instructorForm.value, this.instructorId);
        this.instructorService.put(instructorUpdateCommand)
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
        const instructorCreateCommand: InstructorCreateCommand = this.instructorForm.value;
        this.instructorService.post(instructorCreateCommand).subscribe(() => {
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
      this.formService.validForm(this.instructorForm);
    }
  }

  private initForms() {
    this.instructorForm = this.formBuilder.group({
      cpf: this.formBuilder.control('', [this.isValidCpf]),
      name: this.formBuilder.control('', [Validators.required]),
      cellPhone: this.formBuilder.control(''),
      email: this.formBuilder.control('', [Validators.email])
    });
  }

  isValidCpf(control: AbstractControl): { [p: string]: boolean } {
    return CpfValidator.validate(control);
  }

  getInstructor() {
    this.isLoading = true;
    this.instructorService.getById(this.instructorId).subscribe(instructorResponse => {
      this.instructorForm.patchValue(instructorResponse);
      this.isLoading = false;
    }, error => {
      this.isLoading = false;
      this.toastrService.error('Ocorreu erro na solicitação');
    });
  }
}
