import { Component, OnInit } from '@angular/core';
import { Page } from '../../../shared/models';
import { CompanyService } from '../shared/company.service';
import { BsModalRef, BsModalService, ModalOptions } from 'ngx-bootstrap';
import { CompanyCreateUserComponent } from '../company-create-user/company-create-user.component';
import { CompanyEditComponent } from '../company-edit/company-edit.component';

@Component({
  selector: 'app-companies-list',
  templateUrl: './companies-list.component.html',
  styleUrls: ['./companies-list.component.scss']
})
export class CompaniesListComponent implements OnInit {
  page: Page;
  rows = [];
  isLoading: boolean;

  bsModalRef: BsModalRef;

  constructor(private companyService: CompanyService,
    private modalService: BsModalService) {
    this.page = new Page();
    this.page.pageNumber = 1;
    this.page.size = 10;
  }

  pageInfoInitial = { size: 10, offset: 0 };

  ngOnInit() {
    this.setPage(this.pageInfoInitial);
  }

  columns = [
    { prop: 'id', name: 'ID' },
    { prop: 'corporateName', name: 'Razão Social' },
    { prop: 'tradeName', name: 'Nome Fantasia' },
    { prop: 'cnpj', name: 'CNPJ' },
    { prop: 'hasUser', name: 'Tem Usúario' },
    { prop: 'active', name: 'Ativo' }
  ];

  messages: any = {
    emptyMessage: 'Não há dados para exibição',
    totalMessage: 'total',
    selectedMessage: 'selecionado'
  };

  setPage(pageInfo: any, filter?: string): void {
    this.isLoading = true;
    this.page.pageNumber = pageInfo.offset;
    this.companyService.getAll(this.page, filter).subscribe(pagedData => {
      this.page.size = pagedData.size;
      this.page.pageNumber = pagedData.pageable.pageNumber;
      this.page.totalPages = pagedData.totalPages;
      this.page.totalElements = pagedData.totalElements;
      this.rows = pagedData.content;
      this.isLoading = false;
    });
  }

  newCompany(event) {
    const config: ModalOptions = {
      backdrop: true,
      ignoreBackdropClick: true
    };
    this.bsModalRef = this.modalService.show(CompanyEditComponent, config);
    this.bsModalRef.content.onClose.subscribe(result => {
      this.setPage(this.pageInfoInitial);
    });
  }

  updateCompany(companyId) {
    const initialState = {
      companyId: companyId,
    };
    const config: ModalOptions = {
      initialState: initialState,
      backdrop: true,
      ignoreBackdropClick: true
    };
    this.bsModalRef = this.modalService.show(CompanyEditComponent, config);
    this.bsModalRef.content.onClose.subscribe(result => {
      this.setPage(this.pageInfoInitial);
    });
  }

  deleteCompany(companyId) {

  }

  searchCompany(search) {
    this.setPage(this.pageInfoInitial, search.search);
  }

  createUserCompany(companyId: number) {
    const initialState = {
      companyId: companyId,
      hasUser: this.hasUser(companyId)
    };

    const config: ModalOptions = {
      initialState: initialState,
      backdrop: true,
      ignoreBackdropClick: true
    };
    this.bsModalRef = this.modalService.show(CompanyCreateUserComponent, config);
    this.bsModalRef.content.onClose.subscribe(result => {
      this.setPage(this.pageInfoInitial);
    });
  }

  updateUserCompany(companyId: number) {
    const initialState = {
      companyId: companyId,
      hasUser: this.hasUser(companyId)
    };

    const config: ModalOptions = {
      initialState: initialState,
      backdrop: true,
      ignoreBackdropClick: true
    };
    this.bsModalRef = this.modalService.show(CompanyCreateUserComponent, config);
    this.bsModalRef.content.onClose.subscribe(result => {
      this.setPage(this.pageInfoInitial);
    });
  }

  hasUser(companyId): boolean {
    return this.rows.find(p => p.id == companyId).hasUser;
  }

  showEnable(companyId): boolean {
    return this.hasUser(companyId) && this.isActive(companyId);
  }

  showDisable(companyId): boolean {
    return this.hasUser(companyId) && !this.isActive(companyId);
  }

  isActive(companyId): boolean{
    return this.rows.find(p => p.id == companyId).active;
  }
}
