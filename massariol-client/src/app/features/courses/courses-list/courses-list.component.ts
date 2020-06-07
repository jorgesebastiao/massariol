import { Component, OnInit } from '@angular/core';
import { Page } from '../../../shared/models';
import { BsModalRef, BsModalService, ModalOptions } from 'ngx-bootstrap';
import { CourseService } from '../shared/course.service';
import { CourseEditComponent } from '../course-edit/course-edit.component';

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.scss']
})
export class CoursesListComponent implements OnInit {

  page: Page;
  rows = [];
  isLoading: boolean;

  bsModalRef: BsModalRef;
  constructor(private courseService: CourseService,
    private modalService: BsModalService) {
    this.page = new Page();
  }

  pageInfoInitial = { size: 10, offset: 0 };

  ngOnInit() {
    this.setPage(this.pageInfoInitial);
  }

  columns = [
    { prop: 'id', name: 'ID' },
    { prop: 'name', name: 'Nome' },
    { prop: 'workload', name: 'Carga Horária' },
    { prop: 'validityInYears', name: 'Valdiade em Anos' }
  ];

  messages: any = {
    emptyMessage: 'Não há dados para exibição',
    totalMessage: 'total',
    selectedMessage: 'selecionado'
  };

  setPage(pageInfo: any, filter?: string): void {
    this.isLoading = true;
    this.page.pageNumber = pageInfo.offset;
    this.courseService.getAll(this.page, filter).subscribe(pagedData => {
      this.page.size = pagedData.size;
      this.page.pageNumber = pagedData.pageable.pageNumber;
      this.page.totalPages = pagedData.totalPages;
      this.page.totalElements = pagedData.totalElements;
      this.rows = pagedData.content;
      this.isLoading = false;
    });
  }

  searchCourse(search) {
    this.setPage(this.pageInfoInitial, search.search);
  }

  newCourse(newCourse){
    const initialState = {
      newCourse: newCourse,
    };

    const config: ModalOptions = {
      initialState: initialState,
      class:'modal-lg',
      backdrop: true,
      ignoreBackdropClick: true
    };
    this.bsModalRef = this.modalService.show(CourseEditComponent, config);
    this.bsModalRef.content.onClose.subscribe(result => {
      this.setPage(this.pageInfoInitial);
    });
  }

  updateCourse(courseId){
    const initialState = {
      courseId: courseId,
    };
    const config: ModalOptions = {
      initialState: initialState,
      class:'modal-lg',
      backdrop: true,
      ignoreBackdropClick: true
    };
    this.bsModalRef = this.modalService.show(CourseEditComponent, config);
    this.bsModalRef.content.onClose.subscribe(result => {
      this.setPage(this.pageInfoInitial);
    });
  }

  deleteCourse(courseId){

  }
}