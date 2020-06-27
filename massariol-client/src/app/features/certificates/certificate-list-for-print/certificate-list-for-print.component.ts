import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { Page } from '../../../shared/models';
import { CertificateService } from '../shared/certificate.service';
import { TrainingService } from '../../trainings/shared/training.service';

@Component({
  selector: 'app-certificate-list-for-print',
  templateUrl: './certificate-list-for-print.component.html',
  styleUrls: ['./certificate-list-for-print.component.scss']
})
export class CertificateListForPrintComponent implements OnInit {
  page: Page;
  rows = [];

  title: string;
  businessStudentId: number;
  studentName: string;
  isLoading: boolean;

  constructor(public bsModalRef: BsModalRef,
    private certificateService: CertificateService,
    private trainingService: TrainingService) {
    this.page = new Page();
    this.page.pageNumber = 1;
    this.page.size = 10;
  }

  pageInfoInitial = { size: 10, offset: 0 };

  ngOnInit() {
    this.title = 'Certificados do aluno ' + this.studentName;
    this.setPage(this.pageInfoInitial);
  }

  columns = [
    { prop: 'id', name: 'ID' },
    { prop: 'startDate', name: 'startDate' },
    { prop: 'finishDate', name: 'finishDate' },
    { prop: 'expirationDate', name: 'expirationDate' },
    { prop: 'courseName', name: 'courseName' },
    { prop: 'businessStudentId', name: 'businessStudentId'}
  ];

  messages: any = {
    emptyMessage: 'Não há dados para exibição',
    totalMessage: 'total',
    selectedMessage: 'selecionado'
  };

  setPage(pageInfo: any, filter?: string): void {
    this.isLoading = true;
    this.page.pageNumber = pageInfo.offset;
    this.trainingService.getAllByBusinessStudentId(this.businessStudentId, this.page).subscribe(pagedData => {
      this.page.size = pagedData.size;
      this.page.pageNumber = pagedData.pageable.pageNumber;
      this.page.totalPages = pagedData.totalPages;
      this.page.totalElements = pagedData.totalElements;
      this.rows = pagedData.content;
      this.isLoading = false;
    });
  }

  printCertificate(trainingId: number, businessStudentId: number) {
    this.isLoading = true;
    this.certificateService.getReport(trainingId, businessStudentId).subscribe(x => {
      const url = window.URL.createObjectURL(x);
      window.open(url);
      this.isLoading = false;
    });
  }
}
