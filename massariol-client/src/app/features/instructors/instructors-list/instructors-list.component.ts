import {Component, OnInit} from '@angular/core';
import {Page} from '../../../shared/models';
import {InstructorService} from '../shared/instructor.service';
import {BsModalRef, BsModalService, ModalOptions} from 'ngx-bootstrap/modal';
import {InstructorEditComponent} from '../instructor-edit/instructor-edit.component';

@Component({
  selector: 'app-instructors-list',
  templateUrl: './instructors-list.component.html',
  styleUrls: ['./instructors-list.component.scss']
})
export class InstructorsListComponent implements OnInit {

  page: Page;
  rows = [];
  isLoading: boolean;

  bsModalRef: BsModalRef;

  constructor(private  instructorService: InstructorService,
              private modalService: BsModalService) {
    this.page = new Page();
    this.page.pageNumber = 1;
    this.page.size = 10;
  }

  pageInfoInitial = {size: 10, offset: 0};


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

  ngOnInit() {
    this.setPage(this.pageInfoInitial);
  }

  setPage(pageInfo: any, filter?: string): void {
    this.isLoading = true;
    this.page.pageNumber = pageInfo.offset;
    this.instructorService.getAll(this.page, filter).subscribe(pagedData => {
      this.page.size = pagedData.size;
      this.page.pageNumber = pagedData.pageable.pageNumber;
      this.page.totalPages = pagedData.totalPages;
      this.page.totalElements = pagedData.totalElements;
      this.rows = pagedData.content;
      this.isLoading = false;
    });
  }

  newInstructor(newInstructor) {
    const initialState = {
      newInstructor: newInstructor,
    };

    const config: ModalOptions = {
      initialState: initialState,
      backdrop: true,
      ignoreBackdropClick: true
    };
    this.bsModalRef = this.modalService.show(InstructorEditComponent, config);
    this.bsModalRef.content.onClose.subscribe(result => {
      this.setPage(this.pageInfoInitial);
    });
  }

  searchInstructor(search) {
    this.setPage(this.pageInfoInitial, search.search);
  }

  updateInstructor(instructorId) {
    const initialState = {
      instructorId: instructorId,
    };
    const config: ModalOptions = {
      initialState: initialState,
      backdrop: true,
      ignoreBackdropClick: true
    };
    this.bsModalRef = this.modalService.show(InstructorEditComponent, config);
    this.bsModalRef.content.onClose.subscribe(result => {
      this.setPage(this.pageInfoInitial);
    });
  }

  deleteInstructor(instructorId){

  }
}
