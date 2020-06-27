import { Component, OnInit } from '@angular/core';
import { Page } from '../../../shared/models';
import { SupervisorService } from '../shared/supervisor.service';
import { SupervisorEditComponent } from '../supervisor-edit/supervisor-edit.component';
import { ModalOptions, BsModalRef, BsModalService } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-supervisors-list',
  templateUrl: './supervisors-list.component.html',
  styleUrls: ['./supervisors-list.component.scss']
})
export class SupervisorsListComponent implements OnInit {

  page: Page;
  rows = [];
  isLoading: boolean;

  bsModalRef: BsModalRef;

  constructor(private supervisorService: SupervisorService,
              private modalService: BsModalService) {
    this.page = new Page();
  }

  pageInfoInitial = { size: 10, offset: 0 };


  columns = [
    { prop: 'id', name: 'ID' },
    { prop: 'name', name: 'Nome' },
    { prop: 'cpf', name: 'Cpf' },
    { prop: 'cellPhone', name: 'Celular' },
    { prop: 'email', name: 'E-mail' }
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
    this.supervisorService.getAll(this.page, filter).subscribe(pagedData => {
      this.page.size = pagedData.size;
      this.page.pageNumber = pagedData.pageable.pageNumber;
      this.page.totalPages = pagedData.totalPages;
      this.page.totalElements = pagedData.totalElements;
      this.rows = pagedData.content;
      this.isLoading = false;
    });
  }

  newSupervisor(newSupervisor) {
    const initialState = {
      newSupervisor: newSupervisor,
    };

    const config: ModalOptions = {
      initialState: initialState,
      backdrop: true,
      ignoreBackdropClick: true
    };
    this.bsModalRef = this.modalService.show(SupervisorEditComponent, config);
    this.bsModalRef.content.onClose.subscribe(result => {
      this.setPage(this.pageInfoInitial);
    });
  }

  searchSupervisor(search) {
    this.setPage(this.pageInfoInitial, search.search);
  }

  updateSupervisor(supervisorId) {
    const initialState = {
      supervisorId: supervisorId,
    };
    const config: ModalOptions = {
      initialState: initialState,
      backdrop: true,
      ignoreBackdropClick: true
    };
    this.bsModalRef = this.modalService.show(SupervisorEditComponent, config);
    this.bsModalRef.content.onClose.subscribe(result => {
      this.setPage(this.pageInfoInitial);
    });
  }

  deleteSupervisor(supervisorId) {

  }
}
