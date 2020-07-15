import {OnInit, Component, ViewChild, ElementRef, AfterViewInit} from '@angular/core';

import {SignaturePad} from 'angular2-signaturepad/signature-pad';
import {concat, Observable, of, Subject} from 'rxjs';
import {Page} from '../../../shared/models';
import {catchError, distinctUntilChanged, map, switchMap, tap} from 'rxjs/operators';
import {InstructorService} from '../../instructors/shared/instructor.service';
import {SupervisorService} from '../../supervisors/shared/supervisor.service';
import {StudentService} from '../../students/shared/student.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FormService} from '../../../shared/services/form.service';
import {SignatureCreateCommand} from '../shared/signature.model';
import {SignatureService} from '../shared/signature.service';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signature-edit',
  templateUrl: './signature-edit.component.html',
  styleUrls: ['./signature-edit.component.scss']
})
export class SignatureEditComponent implements OnInit, AfterViewInit  {

  @ViewChild(SignaturePad) signaturePad: SignaturePad;

  signatureForm: FormGroup;

  signatureImage: string;

  isLoading: boolean;

  students: Observable<any[]>;
  studentLoading = false;
  studentInput = new Subject<string>();

  instructors: Observable<any[]>;
  instructorLoading = false;
  instructorInput = new Subject<string>();

  supervisors: Observable<any[]>;
  supervisorLoading = false;
  supervisorInput = new Subject<string>();

  public signaturePadOptions: Object = {
    'canvasWidth': 300,
    'canvasHeight': 300
  };

  constructor(private formBuilder: FormBuilder,
              private formService: FormService,
              private studentService: StudentService,
              private instructorService: InstructorService,
              private supervisorService: SupervisorService,
              private signatureService: SignatureService,
              private toastrService: ToastrService,
              private router: Router) {
  }

  ngOnInit() {
    this.getStudents();
    this.getInstructors();
    this.getSupervisors();
    this.initForms();
    this.initSelect();
  }

  ngAfterViewInit() {
    this.signaturePad.set('canvasWidth', document.getElementById('signaturePad').offsetWidth);
  }

  initForms() {
    this.signatureForm = this.formBuilder.group({
      type: this.formBuilder.control('', [Validators.required]),
      entityId: this.formBuilder.control('', [Validators.required]),
    });
  }

  initSelect() {
    this.signatureForm.controls['type'].setValue('2');
  }

  getStudents() {
    const page: Page = new Page();
    this.students = concat(
      of([]),
      this.studentInput.pipe(
        distinctUntilChanged(),
        tap(() => this.studentLoading = true),
        switchMap(term => this.studentService.getAll(page, term).pipe(
          map((data) => data.content),
          catchError(() => of([])),
          tap(() => this.studentLoading = false)
        ))
      )
    );
  }

  getInstructors() {
    const page: Page = new Page();
    this.instructors = concat(
      of([]),
      this.instructorInput.pipe(
        distinctUntilChanged(),
        tap(() => this.instructorLoading = true),
        switchMap(term => this.instructorService.getAll(page, term).pipe(
          map((data) => data.content),
          catchError(() => of([])),
          tap(() => this.instructorLoading = false)
        ))
      )
    );
  }

  getSupervisors() {
    const page: Page = new Page();
    this.supervisors = concat(
      of([]),
      this.supervisorInput.pipe(
        distinctUntilChanged(),
        tap(() => this.supervisorLoading = true),
        switchMap(term => this.supervisorService.getAll(page, term).pipe(
          map((data) => data.content),
          catchError(() => of([])),
          tap(() => this.supervisorLoading = false)
        ))
      )
    );
  }

  drawComplete() {
    this.signatureImage = this.signaturePad.toDataURL();
  }

  clearSignature() {
    this.signaturePad.clear();
  }

  clearPerson() {
    this.signatureForm.controls['entityId'].setValue(null);
  }


  resizeSignaturePad() {
    this.signaturePad.set('canvasWidth', document.getElementById('signaturePad').offsetWidth);
  }

  saveSignature() {
    this.isLoading = true;
    const signatureCreateCommand: SignatureCreateCommand = this.signatureForm.value;
    signatureCreateCommand.signature = this.signatureImage;

    this.signatureService.post(signatureCreateCommand).subscribe(() => {
      this.toastrService.success('Assinatura cadastrada com Sucesso!');
      this.isLoading = false;
      this.router.navigateByUrl('/');
    }, error => {
      this.isLoading = false;
      const errorMessage = error.status == 400 ? error.error.message : 'Ocorreu erro ao processar a solicitação';
      this.toastrService.error(errorMessage);
    });
  }
}
