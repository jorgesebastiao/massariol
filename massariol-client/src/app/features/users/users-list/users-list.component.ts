import { Component, OnInit } from '@angular/core';
import { Page } from '../../../shared/models';
import { BsModalRef, BsModalService, ModalOptions } from 'ngx-bootstrap/modal';
import { UserService } from '../shared/user.service';
import { UserEditComponent } from '../user-edit/user-edit-component';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent implements OnInit {

  page: Page;
  rows = [];
  isLoading: boolean;

  bsModalRef: BsModalRef;


  constructor(private userService: UserService,
    private modalService: BsModalService) {
    this.page = new Page();
  }

  pageInfoInitial = { size: 10, offset: 0 };

  columns = [
    { prop: 'id', name: 'ID' },
    { prop: 'name', name: 'Nome' },
    { prop: 'email', name: 'E-mail' },
    { prop: 'active', name: 'Ativo' }
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
    /*this.isLoading = true;
    this.page.pageNumber = pageInfo.offset;
    this.supervisorService.getAll(this.page, filter).subscribe(pagedData => {
      this.page.size = pagedData.size;
      this.page.pageNumber = pagedData.pageable.pageNumber;
      this.page.totalPages = pagedData.totalPages;
      this.page.totalElements = pagedData.totalElements;
      this.rows = pagedData.content;
      this.isLoading = false;
    });*/
  }

  newUser(newUser) {
    const initialState = {
      newUser: newUser,
    };

    const config: ModalOptions = {
      initialState: initialState,
      backdrop: true,
      ignoreBackdropClick: true
    };
    this.bsModalRef = this.modalService.show(UserEditComponent, config);
    this.bsModalRef.content.onClose.subscribe(result => {
      this.setPage(this.pageInfoInitial);
    });
  }

  searchUser(search) {
    this.setPage(this.pageInfoInitial, search.search);
  }

  updateUser(userId) {
    const initialState = {
      userId: userId,
    };
    const config: ModalOptions = {
      initialState: initialState,
      backdrop: true,
      ignoreBackdropClick: true
    };
    this.bsModalRef = this.modalService.show(UserEditComponent, config);
    this.bsModalRef.content.onClose.subscribe(result => {
      this.setPage(this.pageInfoInitial);
    });
  }

  deleteUser(userId) {

  }
}