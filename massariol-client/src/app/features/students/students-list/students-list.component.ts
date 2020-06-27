import { StudentService } from '../shared/student.service';
import { BsModalService, BsModalRef, ModalOptions } from 'ngx-bootstrap/modal';
import { Page } from '../../../shared/models';
import { Component, OnInit } from '@angular/core';
import { StudentEditComponent } from '../student-edit/student-edit.component';


@Component({
    selector: 'app-students-list',
    templateUrl: './students-list.component.html',
    styleUrls: ['./students-list.component.scss']
  })
  export class StudentsListComponent implements OnInit {
 
    page: Page;
    rows = [];
    isLoading: boolean;
  
    bsModalRef: BsModalRef;
  
    constructor(private  studentService: StudentService,
                private modalService: BsModalService) {
      this.page = new Page();
    }
    
    ngOnInit() {
      this.setPage(this.pageInfoInitial);
    }
  
    columns = [
      {prop: 'id', name: 'ID'},
      {prop: 'name', name: 'Nome'},
      {prop: 'cpf', name: 'Cpf'},
      {prop: 'cellPhone', name: 'Celular'},
      {prop: 'email', name: 'E-mail'}
    ];
  
    messages: any = {
      emptyMessage: 'Não há dados para exibição',
      totalMessage: 'total',
      selectedMessage: 'selecionado'
    };
  
    pageInfoInitial = { size: 10, offset: 0 };

    setPage(pageInfo: any, filter?: string): void {
      this.isLoading = true;
      this.page.pageNumber = pageInfo.offset;
      this.studentService.getAll(this.page, filter).subscribe(pagedData => {
        this.page.size = pagedData.size;
        this.page.pageNumber = pagedData.pageable.pageNumber;
        this.page.totalPages = pagedData.totalPages;
        this.page.totalElements = pagedData.totalElements;
        this.rows = pagedData.content;
        this.isLoading = false;
      });
    }
  
    searchStudent(search) {
      this.setPage(this.pageInfoInitial, search.search);
    }
  
    newStudent(event){
      const config: ModalOptions = {
        backdrop: true,
        ignoreBackdropClick: true
      };
      this.bsModalRef = this.modalService.show(StudentEditComponent, config);
      this.bsModalRef.content.onClose.subscribe(result => {
        this.setPage(this.pageInfoInitial);
      });
    }
  
    updateStudent(studentId){
      const initialState = {
        studentId: studentId,
      };
      const config: ModalOptions = {
        initialState: initialState,
        backdrop: true,
        ignoreBackdropClick: true
      };
      this.bsModalRef = this.modalService.show(StudentEditComponent, config);
      this.bsModalRef.content.onClose.subscribe(result => {
        this.setPage(this.pageInfoInitial);
      });
    }
  
    deleteStudent(studentId){
  
    }
}