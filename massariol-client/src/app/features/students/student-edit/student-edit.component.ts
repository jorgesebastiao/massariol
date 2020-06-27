import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { FormService } from '../../../shared/services/form.service';
import { StudentService } from '../shared/student.service';
import { ToastrService } from 'ngx-toastr';
import { CpfValidator } from '../../../shared/validators';
import { StudentUpdateCommand, StudentCreateCommand } from '../shared/student.model';

@Component({
  selector: 'app-student-edit',
  templateUrl: './student-edit.component.html',
  styleUrls: ['./student-edit.component.scss']
})
export class StudentEditComponent implements OnInit {
  public onClose: Subject<boolean>;
  studentForm: FormGroup;
  title: string;
  studentId: number;
  isLoading: boolean;
  isEdit: boolean;

  constructor(public bsModalRef: BsModalRef,
    private formBuilder: FormBuilder,
    private formService: FormService,
    private studentService: StudentService,
    private toastrService: ToastrService) {
  }

  ngOnInit() {
    this.onClose = new Subject();
    this.isEdit = false;
    this.title = 'Cadastro de aluno';
    this.initForms();
    if (this.studentId) {
      this.isEdit = true;
      this.title = 'Edição de aluno';
      this.getStudent();
    }
  }

  save() {
    if (this.studentForm.valid) {
      this.isLoading = true;
      if (this.isEdit) {
        const studentUpdateCommand: StudentUpdateCommand = new StudentUpdateCommand(this.studentForm.value, this.studentId);
        this.studentService.put(studentUpdateCommand)
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
        const studentCreateCommand: StudentCreateCommand = this.studentForm.value;
        this.studentService.post(studentCreateCommand).subscribe(() => {
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
      this.formService.validForm(this.studentForm);
    }
  }

  private initForms() {
    this.studentForm = this.formBuilder.group({
      cpf: this.formBuilder.control('', [this.isValidCpf]),
      name: this.formBuilder.control('', [Validators.required]),
      office: this.formBuilder.control(''),
      cellPhone: this.formBuilder.control(''),
      email: this.formBuilder.control('', [Validators.email])
    });
  }

  isValidCpf(control: AbstractControl): { [p: string]: boolean } {
    return CpfValidator.validate(control);
  }

  getStudent() {
    this.isLoading = true;
    this.studentService.getById(this.studentId).subscribe(studentResponse => {
      this.studentForm.patchValue(studentResponse);
      this.isLoading = false;
    }, error => {
      this.isLoading = false;
      this.toastrService.error('Ocorreu erro na solicitação');
    });
  }
}  