import { Component, OnInit } from '@angular/core';
import { Subject, Observable, concat, of } from 'rxjs';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { BsModalRef, BsDatepickerConfig, defineLocale, BsLocaleService, ptBrLocale } from 'ngx-bootstrap';
import { FormService } from '../../../shared/services/form.service';
import { TrainingService } from '../shared/training.service';
import { ToastrService } from 'ngx-toastr';
import { TrainingUpdateCommand, TrainingCreateCommand } from '../shared/training.model';
import { StudentService } from '../../students/shared/student.service';
import { Page } from '../../../shared/models';
import { catchError, debounceTime, distinctUntilChanged, switchMap, tap, map } from 'rxjs/operators';
import { CompanyService } from '../../companies/shared/company.service';
import { CourseService } from '../../courses/shared/course.service';
import { InstructorService } from '../../instructors/shared/instructor.service';
import { SupervisorService } from '../../supervisors/shared/supervisor.service';

@Component({
    selector: 'app-training-edit',
    templateUrl: './training-edit.component.html',
    styleUrls: ['./training-edit.component.scss']
})
export class TrainingEditComponent implements OnInit {
    public onClose: Subject<boolean>;
    locale = 'pt-br';
    maxDate: Date;
    colorTheme = 'theme-default';
    bsConfig: Partial<BsDatepickerConfig>;
    trainingForm: FormGroup;
    title: string;
    trainingId: number;
    isLoading: boolean;
    isEdit: boolean;

    students: Observable<any[]>;
    studentLoading = false;
    studentInput = new Subject<string>();

    companies: Observable<any[]>;
    companyLoading = false;
    companyInput = new Subject<string>();

    courses: Observable<any[]>;
    courseLoading = false;
    courseInput = new Subject<string>();

    instructors: Observable<any[]>;
    instructorLoading = false;
    instructorInput = new Subject<string>();

    supervisors: Observable<any[]>;
    supervisorLoading = false;
    supervisorInput = new Subject<string>();

    constructor(public bsModalRef: BsModalRef,
        private formBuilder: FormBuilder,
        private formService: FormService,
        private localeService: BsLocaleService,
        private trainingService: TrainingService,
        private toastrService: ToastrService,
        private studentService: StudentService,
        private companyService: CompanyService,
        private courseService: CourseService,
        private instructorService: InstructorService,
        private supervisorService: SupervisorService) {

        defineLocale('pt-br', ptBrLocale);
        this.localeService.use(this.locale);
        this.maxDate = new Date(2099, 1);
        this.maxDate.setDate(this.maxDate.getDate());

        this.bsConfig = Object.assign({}, {
            containerClass: this.colorTheme,
            minDate: new Date(1900, 1),
            maxDate: this.maxDate
        });

    }

    ngOnInit() {
        this.onClose = new Subject();
        this.isEdit = false;
        this.title = 'Cadastro de treinamento';
        this.initForms();
        this.getStudents();
        this.getCourses();
        this.getCompanies();
        this.getInstructors();
        this.getSupervisors();

        if (this.trainingId) {
            this.isEdit = true;
            this.title = 'Edição de treinamento';
            this.getTraining();
        }
    }

    initForms() {
        this.trainingForm = this.formBuilder.group({
            startDate: this.formBuilder.control('', [Validators.required]),
            finishDate: this.formBuilder.control('', [Validators.required]),
            realizationDate: this.formBuilder.control('', [Validators.required]),
            courseId: this.formBuilder.control('', [Validators.required]),
            studentId: this.formBuilder.control('', [Validators.required]),
            companyId: this.formBuilder.control('', [Validators.required]),
            instructorId: this.formBuilder.control('', [Validators.required]),
            supervisorId: this.formBuilder.control('', [Validators.required])
        });
    }

    save() {
        if (this.trainingForm.valid) {
            this.isLoading = true;
            if (this.isEdit) {
                const trainingUpdateCommand: TrainingUpdateCommand = new TrainingUpdateCommand(this.trainingForm.value, this.trainingId);
                this.trainingService.put(trainingUpdateCommand)
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
                const trainingCreateCommand: TrainingCreateCommand = this.trainingForm.value;
                this.trainingService.post(trainingCreateCommand).subscribe(() => {
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
            this.formService.validForm(this.trainingForm);
        }
    }

    getTraining() {
        this.isLoading = true;
        this.trainingService.getById(this.trainingId).subscribe(trainingResponse => {
            trainingResponse.startDate = new Date(trainingResponse.startDate);
            trainingResponse.finishDate = new Date(trainingResponse.finishDate);
            trainingResponse.realizationDate = new Date(trainingResponse.realizationDate);

            this.setStudent(trainingResponse.student);
            this.setInstructor(trainingResponse.instructor);
            this.setCompany(trainingResponse.company);
            this.setCourse(trainingResponse.course);
            this.setSupervisor(trainingResponse.supervisor);

            this.trainingForm.patchValue(trainingResponse);
            this.isLoading = false;
        }, error => {
            this.isLoading = false;
            this.toastrService.error('Ocorreu erro na solicitação');
        });
    }

    setStudent(student: any) {
        var newStudents: any[] = [];
        newStudents.push(student);
        const page: Page = new Page();
        this.students = concat(
            of(newStudents),
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

    setInstructor(instructor: any) {
        var newInstructors: any[] = [];
        newInstructors.push(instructor);
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

    setCompany(company: any) {

    }

    setCourse(company: any) {

    }

    setSupervisor(supervisor: any) {

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

    getCourses() {
        const page: Page = new Page();
        this.courses = concat(
            of([]),
            this.courseInput.pipe(
                distinctUntilChanged(),
                tap(() => this.courseLoading = true),
                switchMap(term => this.courseService.getAll(page, term).pipe(
                    map((data) => data.content),
                    catchError(() => of([])),
                    tap(() => this.courseLoading = false)
                ))
            )
        );
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
}  