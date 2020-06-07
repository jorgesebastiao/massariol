import {Component, OnInit} from '@angular/core';
import {StudentService} from '../../students/shared/student.service';
import {Page} from '../../../shared/models';
import {BsModalRef, BsModalService, ModalOptions} from 'ngx-bootstrap';
import {CertificateListForPrintComponent} from '../certificate-list-for-print/certificate-list-for-print.component';
import { BusinessStudentService } from '../../business-students/shared/business-student.service';

@Component({
  selector: 'app-certificates-list',
  templateUrl: './certificates-list.component.html',
  styleUrls: ['./certificates-list.component.scss']
})
export class CertificatesListComponent implements OnInit {
  page: Page;
  rows = [];
  isLoading: boolean;

  bsModalRef: BsModalRef;

  constructor(private studentService: StudentService,
              private businessStudentService: BusinessStudentService,
              private modalService: BsModalService) {
    this.page = new Page();
    this.page.pageNumber = 1;
    this.page.size = 10;
  }

  /*
    ngOnInit() {
      /*this.certificateService.getReport(800, 'Jorge').subscribe( x =>{
        console.log(x);
        const url = window.URL.createObjectURL(x);
        window.open(url);
      });
    }*/

  pageInfoInitial = {size: 10, offset: 0};

  ngOnInit() {
    this.setPage(this.pageInfoInitial);
  }

  columns = [
    {prop: 'id', name: 'ID'},
    {prop: 'studentName', name: 'Nome'},
    {prop: 'studentCpf', name: 'Cpf'},
    {prop: 'studentOffice', name: 'Função'}
  ];

  messages: any = {
    emptyMessage: 'Não há dados para exibição',
    totalMessage: 'total',
    selectedMessage: 'selecionado'
  };

  setPage(pageInfo: any, filter?: string): void {
    this.isLoading = true;
    this.page.pageNumber = pageInfo.offset;
    this.businessStudentService.getAll(this.page, filter).subscribe(pagedData => {
      this.page.size = pagedData.size;
      this.page.pageNumber = pagedData.pageable.pageNumber;
      this.page.totalPages = pagedData.totalPages;
      this.page.totalElements = pagedData.totalElements;
      this.rows = pagedData.content;
      this.isLoading = false;
    });
  }

  searchStudents(search) {
    this.setPage(this.pageInfoInitial, search.search);
  }

  openCertificatesForStudent(businessStudentId: number, name: string) {
    const initialState = {
      businessStudentId: businessStudentId,
      studentName: name,
    };
    const config: ModalOptions = {
      initialState: initialState,
      class: 'modal-xl',
      backdrop: true,
      ignoreBackdropClick: true
    };
    this.bsModalRef = this.modalService.show(CertificateListForPrintComponent, config);
  }
}
