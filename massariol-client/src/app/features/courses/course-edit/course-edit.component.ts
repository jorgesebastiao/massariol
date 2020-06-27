import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { CourseService } from '../shared/course.service';
import {
  CourseUpdateCommand,
  CourseCreateCommand
} from '../shared/course.model';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { FormService } from '../../../shared/services/form.service';
import { ToastrService } from 'ngx-toastr';
import { MdEditorOption } from 'ngx-markdown-editor';

@Component({
  selector: 'app-course-edit',
  templateUrl: './course-edit.component.html',
  styleUrls: ['./course-edit.component.scss']
})
export class CourseEditComponent implements OnInit {
  public textEditor;
  public textPreview;
  public modeEditor = 'editor';
  public modePreview = 'preview';

  public optionsEditor: MdEditorOption = {
    resizable: false,
    showPreviewPanel: false,
    hideIcons: ['Image', 'Code', 'FullScreen', 'TogglePreview']
  };

  public optionsPreview: MdEditorOption = {
    resizable: false,
    showPreviewPanel: true
  };

  public onClose: Subject<boolean>;
  courseForm: FormGroup;
  title: string;
  courseId: number;
  isLoading: boolean;
  isEdit: boolean;
  numberPattern = /^[0-9]*$/;

  constructor(
    public bsModalRef: BsModalRef,
    private formBuilder: FormBuilder,
    private formService: FormService,
    private toastrService: ToastrService,
    private courseService: CourseService
  ) {}

  ngOnInit() {
    this.onClose = new Subject();
    this.isEdit = false;
    this.title = 'Cadastro de curso';
    this.initForms();
    if (this.courseId) {
      this.isEdit = true;
      this.title = 'Edição de curso';
      this.getCourse();
    }
  }
  save() {
    if (this.courseForm.valid) {
      this.isLoading = true;
      if (this.isEdit) {
        const courseUpdateCommand: CourseUpdateCommand = new CourseUpdateCommand(
          this.courseForm.value,
          this.courseId,
          this.textEditor
        );
        this.courseService.put(courseUpdateCommand).subscribe(
          () => {
            this.toastrService.success('Edição realizada com Sucesso!');
            this.isLoading = false;
            this.onClose.next(true);
            this.bsModalRef.hide();
          },
          error => {
            this.isLoading = false;
            const errorMessage =
              error.status === 400
                ? error.error.message
                : 'Ocorreu erro ao processar a solicitação';
            this.toastrService.error(errorMessage);
          }
        );
      } else {
        const courseCreateCommand: CourseCreateCommand = this.courseForm.value;
        courseCreateCommand.guideline = this.textEditor;
        this.courseService.post(courseCreateCommand).subscribe(
          () => {
            this.toastrService.success('Cadastro realizado com Sucesso!');
            this.isLoading = false;
            this.onClose.next(true);
            this.bsModalRef.hide();
          },
          error => {
            this.isLoading = false;
            const errorMessage =
              error.status === 400
                ? error.error.message
                : 'Ocorreu erro ao processar a solicitação';
            this.toastrService.error(errorMessage);
          }
        );
      }
    } else {
      this.formService.validForm(this.courseForm);
    }
  }

  private initForms() {
    this.courseForm = this.formBuilder.group({
      name: this.formBuilder.control('', [Validators.required]),
      courseIdentification: this.formBuilder.control('', [Validators.required]),
      workload: this.formBuilder.control('', [
        Validators.required,
        Validators.pattern(this.numberPattern)
      ]),
      validityInYears: this.formBuilder.control('', [
        Validators.required,
        Validators.pattern(this.numberPattern)
      ])
    });
  }

  getCourse() {
    this.isLoading = true;
    this.courseService.getById(this.courseId).subscribe(
      courseResponse => {
        this.courseForm.patchValue(courseResponse);
        this.textEditor = courseResponse.guideline;
        this.textPreview = this.textEditor;
        this.isLoading = false;
      },
      error => {
        this.isLoading = false;
        this.toastrService.error('Ocorreu erro na solicitação');
      }
    );
  }

  updatePreview() {
    this.textPreview = this.textEditor;
  }
}
